package com.example.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.be.service.UserService;

@RestController
@RequestMapping("/api/follow")
public class FlowController {

    @Autowired
    private UserService userService;

    @PostMapping("/followArtist")
    public ResponseEntity<?> flowArtist(@RequestParam("userId") int userId,
            @RequestParam("artistId") int artistId) {
        userService.flowArtist(userId, artistId);
        return ResponseEntity.ok("follow successfully");
    }

    @PatchMapping("/unFlowArtist")
    public ResponseEntity<?> unFollow(@RequestParam("userId") int userId,
            @RequestParam("artistId") int artistId) {
        userService.unFollow(userId, artistId);
        return ResponseEntity.ok("Unfollow successfully");
    }

    @GetMapping("/getFollow")
    public boolean getFollow(@RequestParam("userId") int userId,
            @RequestParam("artistId") int artistId) {
        return userService.getFollow(userId, artistId);
    }

}
