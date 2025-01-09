package com.example.be.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.be.dto.request.InfoAccountRequest;
import com.example.be.dto.response.artist.SelectMusicByArtistIdResponse;
import com.example.be.dto.response.home.PopularArtistResponse;
import com.example.be.dto.response.indexArtist.IndexArtistResponse;
import com.example.be.dto.response.indexUser.FlowingByUserIdResponse;
import com.example.be.dto.response.indexUser.IndexUserResponse;
import com.example.be.dto.response.search.SearchArtistResponse;
import com.example.be.model.Music;
import com.example.be.repository.MusicRepository;
import com.example.be.repository.UserRepository;
import com.example.be.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    MusicRepository musicRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public ResponseEntity<String> getUserProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Xử lý với username (bạn có thể lấy thông tin người dùng từ DB hay thực hiện
        // thao tác khác)
        return ResponseEntity.ok("User: " + username);
    }

    @PostMapping("/popularArtist")
    public List<PopularArtistResponse> popularArtist(@RequestBody InfoAccountRequest info) {
        List<PopularArtistResponse> popularArtistResponse = userService.getPopularArtist(info.getId());
        return popularArtistResponse;
    }

    @GetMapping("/searchArtist")
    public List<SearchArtistResponse> searchMusic(@RequestParam String keyword) {
        return userService.searchArtist(keyword);
    }

    @GetMapping("/getIndexArtist")
    public IndexArtistResponse getIndexArtist(@RequestParam int artistId) {
        return userService.getIndexArtist(artistId);
    }

    @GetMapping("/getIndexUser")
    public IndexUserResponse getIndexUser(@RequestParam int userId) {
        return userService.getIndexUser(userId);
    }

    @GetMapping("/getFlowingByUserId")
    public List<FlowingByUserIdResponse> getFlowingByUserId(@RequestParam int userId) {
        return userService.getFlowingByUserId(userId);
    }

    @GetMapping("/getUserIdByAccountId")
    public ResponseEntity<?> getImage(@RequestParam int accountId) {
        int userId = userService.getUserIdByAccountId(accountId);
        return ResponseEntity.ok(userId);
    }

    @GetMapping("/user-songs/{userId}")
    public ResponseEntity<List<SelectMusicByArtistIdResponse>> getUserSongs(@PathVariable int artistId) {
        List<Music> userSongs = musicRepository.findAllByArtistId(artistId);
        List<SelectMusicByArtistIdResponse> response = userSongs.stream()
                .map(song -> new SelectMusicByArtistIdResponse(song.getId(), song.getMusicName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(
            @RequestParam("userId") int userId,
            @RequestParam("name") String name,
            @RequestParam("artistName") String artistName,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        try {
            userService.updateUserProfile(userId, name, artistName, image);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi cập nhật hồ sơ: " + e.getMessage());
        }
    }

    @PatchMapping("/updateProfileUser")
    public ResponseEntity<?> updateProfileUser(
            @RequestParam("userId") int userId,
            @RequestParam("name") String name,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        try {
            userService.updateUserProfileUser(userId, name, image);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi cập nhật hồ sơ: " + e.getMessage());
        }
    }

    @PatchMapping("/requestBecomeArtist")
    public ResponseEntity<?> requestBecomeArtist(
            @RequestParam("userId") int userId,
            @RequestParam("stageName") String stageName) {
        if (userService.requestToBecomeArtistToUser(userId, stageName)) {
            return ResponseEntity.ok("Đã gửi yêu cầu thành nghệ sĩ");
        }
        return ResponseEntity.ok("Yêu cầu thành nghệ sĩ đã được gửi trước đó");
    }

    @GetMapping("/getNumberOfArtist")
    public Long getNumberOfArtist() {
        return userService.getNumberOfArtist();
    }

    @GetMapping("/getNumberOfUser")
    public Long getNumberOfUser() {
        return userService.getNumberOfUser();
    }

}
