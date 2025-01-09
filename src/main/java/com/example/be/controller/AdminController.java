package com.example.be.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.be.dto.response.admin.HiddenMusicResponse;
import com.example.be.dto.response.admin.ListArtistResponse;
import com.example.be.dto.response.admin.ListUserResponse;
import com.example.be.dto.response.admin.ReportedMusicResponse;
import com.example.be.dto.response.admin.ReportedUserResponse;
import com.example.be.dto.response.admin.RequestArtistResponse;
import com.example.be.service.MusicService;
import com.example.be.service.ReportedMusicService;
import com.example.be.service.ReportedUserService;
import com.example.be.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    ReportedMusicService reportedMuicService;
    @Autowired
    UserService userService;
    @Autowired
    ReportedUserService reportedUserService;
    @Autowired
    MusicService musicService;

    @GetMapping("reportedMusic")
    public List<ReportedMusicResponse> reportedMuisc() {
        return reportedMuicService.getAllRepostedMusic();
    }

    @PostMapping("hiddenReportMusic")
    public ResponseEntity<String> hiddenReportMusic(
            @RequestParam("reportId") int reportId) {
        try {
            musicService.hiddenReportMusic(reportId);
            return new ResponseEntity<>("Đã huỷ bỏ báo cáo này", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving the file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getAllHiddenMusic")
    public List<HiddenMusicResponse> getAllHiddenMusic() {
        return reportedMuicService.getAllHiddenMusic();
    }

    @GetMapping("reportedUser")
    public List<ReportedUserResponse> ReportedUsers() {
        return reportedUserService.getAllRepostedUser();
    }

    @GetMapping("requestToBecomeArtist")
    public List<RequestArtistResponse> requestToBecomeArtist() {
        return userService.requestToBecomeArtist();
    }

    @GetMapping("findAllArtist")
    public List<ListArtistResponse> findAllArtist() {
        return userService.findAllArtist();
    }

    @GetMapping("findAllUser")
    public List<ListUserResponse> findAllUser() {
        return userService.findAllUser();
    }

    @PostMapping("hiddenMusic")
    public ResponseEntity<String> hiddenMusic(
            @RequestParam("reportId") int reportId) {
        try {
            musicService.hiddenMusic(reportId);
            return new ResponseEntity<>("Đã ẩn bỏ bài nhạc", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("acceptRequestArtist")
    public ResponseEntity<String> acceptRequestArtist(
            @RequestParam("userId") int userId) {
        try {
            userService.acceptRequestArtist(userId);
            return new ResponseEntity<>("Đã chấp nhận yêu cầu", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}
