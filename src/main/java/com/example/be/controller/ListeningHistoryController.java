package com.example.be.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.be.dto.request.InfoAccountRequest;
import com.example.be.dto.response.home.RecentlyListenedMusicResponse;
import com.example.be.service.ListeningHistoryService;

@RestController
@RequestMapping("/api/listening-history")
public class ListeningHistoryController {
     @Autowired ListeningHistoryService listeningHistoryService;
    @PostMapping("recently-music")
    public List<RecentlyListenedMusicResponse> HiddenUsers(@RequestBody InfoAccountRequest info){
        return listeningHistoryService.getRecentlyMusic(info.getId());
    }
}
