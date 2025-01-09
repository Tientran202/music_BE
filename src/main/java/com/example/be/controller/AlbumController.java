package com.example.be.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.be.dto.response.home.AlbumTopResponse;
import com.example.be.dto.response.indexAlbumPage.IndexAlbumResponse;
import com.example.be.dto.response.indexArtist.AlbumByArtistIdResponse;
import com.example.be.dto.response.search.SearchAlbumResponse;
import com.example.be.model.Album;
import com.example.be.model.User;
import com.example.be.repository.AlbumRepository;
import com.example.be.repository.UserRepository;
import com.example.be.service.AlbumService;

@RestController
@RequestMapping("/api/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlbumRepository albumRepository;

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

    @GetMapping("getAlbumByArtistIdlimit")
    public List<AlbumByArtistIdResponse> getAlbumByArtistIdlimit(@RequestParam int artistId) {
        return albumService.getAlbumByArtistIdlimit(artistId);
    }

    @GetMapping("getAlbumByArtistId")
    public List<AlbumByArtistIdResponse> getAlbumByArtistId(@RequestParam int artistId) {
        return albumService.getAlbumByArtistId(artistId);
    }
    @GetMapping("getNumberOfAlbum")
    public Long getNumberOfAlbum() {
        return albumService.getNumberOfAlbum();
    }

    

    @PostMapping("/createAlbum")
    public int createAlbum(@RequestParam("album_name") String albumName,
            @RequestParam("user_id") int userId,
            @RequestParam(value = "img", required = false) MultipartFile imgFile) {
        int albumId = 0;
        try {
            // Lưu ảnh vào mảng byte nếu có ảnh
            byte[] imgBytes = null;
            if (imgFile != null) {
                imgBytes = imgFile.getBytes();

                Optional<User> optionalUser = userRepository.findById(userId);
                User user = optionalUser.get();

                Album album = new Album();
                album.setAlbumName(albumName);
                album.setUser(user);
                album.setImg(imgBytes);
                // Lưu vào cơ sở dữ liệu
                album = albumRepository.save(album);
                albumId = album.getId();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return albumId;

    }

}
