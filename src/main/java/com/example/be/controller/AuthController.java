package com.example.be.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.badRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.be.dto.request.LoginRequestDTO;
import com.example.be.dto.request.RegisterUserRequest;
import com.example.be.dto.request.TokenRequest;
import com.example.be.dto.request.updatePassResquest;
import com.example.be.dto.response.AuthResponse;
import com.example.be.dto.response.home.IdRoleResponse;
import com.example.be.jwt.JwtTokenUtil;
import com.example.be.model.Account;
import com.example.be.model.User;
import com.example.be.repository.AccountRepository;
import com.example.be.repository.UserRepository;
import com.example.be.service.AccountService;
import com.example.be.service.EmailService;
import com.example.be.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequest registerUserRequest, BindingResult bindingResult,
            HttpServletRequest request) {

        try {
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
            }
            int config = emailService.existByEmailConfirmation(registerUserRequest.getEmail());
            // if (config == 2) {
            // return ResponseEntity.badRequest().body("Email tồn tại");
            // }
            // if (config == 1) {
            // return ResponseEntity.badRequest().body("Email đã đăng kí những chưa được xác
            // thực");
            // }

            String hashedPassword = passwordEncoder.encode(registerUserRequest.getPassword());
            Account account = new Account();
            account.setUsername(registerUserRequest.getUsername());
            account.setPassword(hashedPassword);
            account.setRegister_date();
            account.setEmail(registerUserRequest.getEmail());
            accountRepository.save(account);
            User user = new User();
            user.setRole("user");
            user.setAccount(account);
            user.setName(registerUserRequest.getName());
            user.setAvatar(userService.getDefaultAvatar());
            userRepository.save(user);

            // accountService.CreateUseClone(registerUserRequest.getEmail(),
            // registerUserRequest.getPassword());

            String subject = "Email authentication";
            String confirmationUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                    + "/api/auth/registration-confirmation?username=" + registerUserRequest.getUsername() + "&password="
                    + registerUserRequest.getPassword() + "&register_date=" + registerUserRequest.getRegister_date()
                    + "&email="
                    + registerUserRequest.getEmail();
            // Send the confirmationmail
            try {
                emailService.sendCustomEmail(registerUserRequest, subject, confirmationUrl);
                return ResponseEntity.ok("Account registered successfully");
            } catch (MessagingException e) {
                return badRequest().body("Failed to send email for verification");
            }
        } catch (Exception e) {
            // Log lỗi ra console
            e.printStackTrace();
            // Trả về lỗi với thông tin chi tiết
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering account");
        }
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequestDTO loginRequest) {
        boolean isAuthenticated = accountService.login(loginRequest);
        String userName = loginRequest.getUsername();
        String role = userService.getRoleByUsername(userName);
        if (isAuthenticated) {
            String accessToken = jwtTokenUtil.generateAccessToken(loginRequest.getUsername());
            String refreshToken = jwtTokenUtil.generateRefreshToken(loginRequest.getUsername());
            accountService.updateAccessTokenAndRefreshToken(userName, accessToken, refreshToken);
            return new AuthResponse(accessToken, refreshToken, role);
        } else {
            throw new RuntimeException("Tên đăng nhập hoặc mật khẩu bị sai");
        }
    }

    @PatchMapping("/updatePass")
    public ResponseEntity<?> updatePass(@RequestBody updatePassResquest updatePassResquest) {
        if (accountService.updatePass(updatePassResquest)) {
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
    }

    // @PostMapping("/authToken")
    // public ResponseEntity<?> authToken(@RequestBody TokenRequest token) {
    // try {
    // // Kiểm tra access_token có hết hạn không
    // if (jwtTokenUtil.isTokenExpired(token.getAccess_token())) {
    // this.refreshToken(token.getRefresh_token());
    // }
    // return ResponseEntity.ok(token.getAccess_token());
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
    // .body("{\"error\": \"invalid_refresh_token\", \"message\": \"Invalid refresh
    // token.\"}");
    // }
    // }

    @GetMapping("/check-token")
    public ResponseEntity<?> checkToken(HttpServletRequest request) {
        // Lấy header Authorization
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Lấy token sau "Bearer "
            try {
                // Kiểm tra token hết hạn
                if (jwtTokenUtil.isTokenExpired(token)) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body("{\"error\": \"access_token_expired\", \"message\": \"Your access token has expired. Please refresh it.\"}");
                }
                // Lấy thông tin từ token
                String username = jwtTokenUtil.getUsernameFromToken(token);
                IdRoleResponse idRoleResponse = accountService.getIdAccountByUserName(username);
                int accountId = idRoleResponse.getAccount_id();
                String role = idRoleResponse.getRole();
                int userId = userService.getUserIdByAccountId(accountId);
                Map<String, Object> response = new HashMap<>();
                response.put("accountId", accountId);
                response.put("role", role);
                response.put("userId", userId);
                return ResponseEntity.ok(response);

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("{\"error\": \"invalid_token\", \"message\": \"Invalid token.\"}");
            }
        }

        // Trường hợp không có token trong header
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("{\"error\": \"missing_token\", \"message\": \"Authorization token is missing.\"}");

    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRequest token) {
        try {
            // Kiểm tra refresh_token
            if (jwtTokenUtil.isTokenExpired(token.getRefresh_token())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("refresh token hêt han ");
            }

            // Tạo mới access_token
            String username = jwtTokenUtil.getUsernameFromToken(token.getRefresh_token());
            String newAccessToken = jwtTokenUtil.generateAccessToken(username);
            accountService.updateAccessToken(username, newAccessToken);
            // Trả về token mới
            return ResponseEntity.ok(Collections.singletonMap("access_token", newAccessToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\": \"invalid_refresh_token\", \"message\": \"Invalid refresh token.\"}");
        }
    }

    @GetMapping("/registration-confirmation")
    public ResponseEntity<?> registrationConfirmation(@RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("register_date") String registerDay,
            @RequestParam("email") String email) {
        if (accountService.authenticated(email)) {
            return ResponseEntity.ok("Tài khoản đã được xác thực email");
        }
        accountService.confirAccount(email);
        return ResponseEntity.ok("Xác thực thành công");
    }

    @GetMapping("/img/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        Optional<User> userImg = userRepository.findById(id);
        if (userImg.isPresent()) {
            byte[] imageData = userImg.get().getAvatar();
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageData);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
