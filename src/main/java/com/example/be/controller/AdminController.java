package com.example.be.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.be.dto.response.admin.HiddenMusicResponse;
import com.example.be.dto.response.admin.ListArtistResponse;
import com.example.be.dto.response.admin.ListUserResponse;
import com.example.be.dto.response.admin.ReportedMusicResponse;
import com.example.be.dto.response.admin.ReportedUserResponse;
import com.example.be.dto.response.admin.RequestArtistResponse;
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

    @GetMapping("reportedMusic")
    public List<ReportedMusicResponse> reportedMuisc() {
        return reportedMuicService.getAllRepostedMusic();
    }

    @GetMapping("hiddenMusic")
    public List<HiddenMusicResponse> hiddenMuisc() {
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
}
