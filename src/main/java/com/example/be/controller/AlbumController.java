package com.example.be.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.be.dto.response.home.AlbumTopResponse;
import com.example.be.dto.response.indexAlbumPage.IndexAlbumResponse;
import com.example.be.dto.response.indexArtist.AlbumByArtistIdResponse;
import com.example.be.dto.response.search.SearchAlbumResponse;
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

    @GetMapping("getSearchAlbum")
    public List<SearchAlbumResponse> searchUsers(@RequestParam String keyword) {
        return albumService.searchAlbum(keyword);
    }

    @GetMapping("getIndexAlbum/{albumId}")
    public IndexAlbumResponse getIndexAlbum(@PathVariable int albumId) {
        return albumService.getIndexAlbum(albumId);
    }

    @GetMapping("getAlbumByArtistId")
    public List<AlbumByArtistIdResponse> getAlbumByArtistId(@RequestParam int artistId) {
        return albumService.getAlbumByArtistId(artistId);
    }

    // @GetMapping("getAllAlbum")
    // public List<AllAlbumResponse> getAllAlbum() {
    //     return albumService.getAllAlbum();
    // }

}
