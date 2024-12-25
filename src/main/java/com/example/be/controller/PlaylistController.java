package com.example.be.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.be.dto.response.indexArtist.PlaylistByArtistIdResponse;
import com.example.be.dto.response.indexUser.PlaylistByUserIdResponse;
import com.example.be.dto.response.search.SearchPlaylistResponse;
import com.example.be.service.PlaylistService;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {


    @Autowired
    private PlaylistService playlistService;

     @GetMapping("/searchPlaylist")
    public List<SearchPlaylistResponse> searchMusic(@RequestParam String keyword) {
        return playlistService.searchPlaylist(keyword);
    }
    @GetMapping("/getPlaylistByArtistId")
    public List<PlaylistByArtistIdResponse> getPlaylistByArtistId(@RequestParam int artistId) {
        return playlistService.getPlaylistByArtistId(artistId);
    }

    @GetMapping("/getPlaylistByUserId")
    public List<PlaylistByUserIdResponse> getPlaylistByUserId(@RequestParam int userId) {
        return playlistService.getPlaylistByUserId(userId);
    }

    


}
