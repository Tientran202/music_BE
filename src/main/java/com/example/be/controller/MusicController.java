package com.example.be.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.be.dto.response.IndexMusicRespone;
import com.example.be.dto.response.home.NewMusicResponse;
import com.example.be.dto.response.search.SearchMusicResponse;
import com.example.be.model.Music;
import com.example.be.repository.MusicRepository;
import com.example.be.service.MusicService;

@RestController
@RequestMapping("/api/music")
public class MusicController {

    @Autowired
    private MusicService musicService;
    @Autowired
    private MusicRepository musicRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> playMusic(@PathVariable int id) {
        return musicService.getMusicById(id)
                .map(music -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add(HttpHeaders.CONTENT_TYPE, "audio/mpeg");
                    return new ResponseEntity<>(music.getAudio(), headers, HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/img2/{id}")
    public ResponseEntity<byte[]> getImage2(@PathVariable int id) {
        Optional<Music> musicImg = musicRepository.findById(id);
        if (musicImg.isPresent()) {
            byte[] imageData = musicImg.get().getImg();
            System.err.println(imageData);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageData);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/getIndexMusicArtist/{id}")
    public ResponseEntity<?> getIndexMusic(@PathVariable int id) {
        IndexMusicRespone indexMusic = musicService.getIndexMusicByMusicId(id);
        return ResponseEntity.ok(indexMusic);
    }

    @GetMapping("/getNewMusic")
    public ResponseEntity<?> getNewMusic() {
        List<NewMusicResponse> NewMusic = musicService.getNewMusic();
        return ResponseEntity.ok(NewMusic);
    }

    @GetMapping("/searchMusic")
    public List<SearchMusicResponse> searchMusic(@RequestParam String keyword) {
        return musicService.searchMusic(keyword);
    }

}
