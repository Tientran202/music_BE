package com.example.be.controller;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.badRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.be.dto.request.LoginRequestDTO;
import com.example.be.dto.request.TokenRequest;
import com.example.be.dto.response.AuthResponse;
import com.example.be.jwt.JwtTokenUtil;
import com.example.be.model.Account;
import com.example.be.model.User;
import com.example.be.repository.UserRepository;
import com.example.be.service.AccountService;
import com.example.be.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

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

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid Account account, BindingResult bindingResult,
            HttpServletRequest request) {

        try {
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
            }
            // check email
            int config = emailService.existByEmailConfirmation(account.getEmail());
            if (config == 2) {
                return ResponseEntity.ok("Email exist");
            }
            if (config == 1) {
                return ResponseEntity.ok("The email exists but the account has not been verified");
            }

            // Create subject for email, confirmation url and hash pass
            String hashedPassword = passwordEncoder.encode(account.getPassword());
            account.setPassword(hashedPassword);
            account.setRegister_date();
            accountService.CreateUseClone(account.getEmail(), account.getPassword());

            String subject = "Email authentication";
            String confirmationUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                    + "/api/auth/registration-confirmation?username=" + account.getUsername() + "&password="
                    + account.getPassword() + "&register_date=" + account.getRegister_date() + "&email="
                    + account.getEmail();
            // Send the confirmationmail
            try {
                emailService.sendCustomEmail(account, subject, confirmationUrl);
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
        String info = loginRequest.getUsername();
        if (isAuthenticated) {
            String accessToken = jwtTokenUtil.generateAccessToken(loginRequest.getUsername());
            String refreshToken = jwtTokenUtil.generateRefreshToken(loginRequest.getUsername());
            accountService.updateAccessTokenAndRefreshToken(info, accessToken, refreshToken);
            return new AuthResponse(accessToken, refreshToken);
        } else {
            throw new RuntimeException("Tên đăng nhập hoặc mật khẩu bị sai");
        }
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

                // Tìm user trong cơ sở dữ liệu
                int userId = accountService.getIdAccountByUserName(username);
                return ResponseEntity.ok(Collections.singletonMap("user_id", userId));

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
        if (!accountService.authenticated(email)) {
            if (accountService.confirmationEmailByPass(email, password)) {
                accountService.register(username, email);
                return ResponseEntity.ok("Tài khoản đã được xác thực email");
            }
        }
        return ResponseEntity.ok("Account registered unsuccessfully");
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
