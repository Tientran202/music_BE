package com.example.be.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.be.dto.response.home.AlbumTopResponse;
import com.example.be.service.AlbumService;

@RestController
@RequestMapping("/api/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping("getTopAlbum")
    public List<AlbumTopResponse> ReportedUsers() {
        return albumService.getTopAlbum();
    }
}
