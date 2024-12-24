package com.example.be.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.be.dto.response.GenreMusicRespone;
import com.example.be.model.Genre;
import com.example.be.service.GenreService;
import com.example.be.service.MusicService;

@RestController
@RequestMapping("/api/genre")
public class GenreController {
    @Autowired
    private MusicService musicService;

    @Autowired
    private GenreService genreService;

    @GetMapping("/getGenreAndMusic/{id}")
    public ResponseEntity<?> getGenreAndMusic(@PathVariable int id) {
        List<GenreMusicRespone> musicList = musicService.getMusicByGenreId(id);
        return ResponseEntity.ok(musicList);
    }
    @GetMapping("/getAllGenre")
    public ResponseEntity<?> getQuantityGenre() {
        List<Genre> listGenres = genreService.getQuantityGenre();
        return  ResponseEntity.ok(listGenres);
    }

}
