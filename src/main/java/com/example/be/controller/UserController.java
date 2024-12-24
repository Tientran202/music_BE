package com.example.be.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.be.dto.request.InfoAccountRequest;
import com.example.be.dto.response.home.PopularArtistResponse;
import com.example.be.dto.response.search.SearchArtistResponse;
import com.example.be.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;

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
}
