package com.example.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.be.service.MusicService;
import com.example.be.service.StoryService;

@RestController
@RequestMapping("api/story")
public class StoryController {
    @Autowired
    MusicService musicService;

    @Autowired
    StoryService storyService;

    @PostMapping("/createStory")
    public ResponseEntity<?> cutMusic(
            @RequestParam("startTime") int startTime,
            @RequestParam("endTime") int endTime,
            @RequestParam("musicId") int musicId,
            @RequestParam("titleStory") String titleStory,
            @RequestParam(value = "imgCover", required = false) MultipartFile imgCover,
            @RequestParam("userId") int userId) {
        try {
            byte[] cutAudioBytes = musicService.cutMusic(musicId, startTime, endTime);
            byte[] imgBytes = imgCover.getBytes();
            storyService.createStory(cutAudioBytes, musicId, titleStory, imgBytes, userId);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

 

    
}
