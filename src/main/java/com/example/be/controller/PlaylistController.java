package com.example.be.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.be.dto.response.indexAlbumPage.IndexAlbumResponse;
import com.example.be.dto.response.indexArtist.PlaylistByArtistIdResponse;
import com.example.be.dto.response.indexUser.PlaylistByUserIdResponse;
import com.example.be.dto.response.search.SearchPlaylistResponse;
import com.example.be.model.Playlist;
import com.example.be.model.User;
import com.example.be.repository.PlaylistRepository;
import com.example.be.repository.UserRepository;
import com.example.be.service.AlbumService;
import com.example.be.service.PlaylistService;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private AlbumService albumService;

    @GetMapping("/searchPlaylist")
    public List<SearchPlaylistResponse> searchMusic(@RequestParam String keyword) {
        return playlistService.searchPlaylist(keyword);
    }

    @GetMapping("/getPlaylistByArtistIdLimit")
    public List<PlaylistByArtistIdResponse> getPlaylistByArtistIdLimit(@RequestParam int artistId) {
        return playlistService.getPlaylistByArtistIdLimit(artistId);
    }

    @GetMapping("/getPlaylistByArtistId")
    public List<PlaylistByArtistIdResponse> getPlaylistByArtistId(@RequestParam int artistId) {
        return playlistService.getPlaylistByArtistId(artistId);
    }

    @GetMapping("getIndexPlaylist/{playlistId}")
    public IndexAlbumResponse getIndexPlaylist(@PathVariable int playlistId) {
        return playlistService.getIndexPlaylist(playlistId);
    }

    @GetMapping("/getPlaylistByUserIdLimit")
    public List<PlaylistByUserIdResponse> getPlaylistByUserIdLimit(@RequestParam int userId) {
        return playlistService.getPlaylistByUserIdLimit(userId);
    }

    @GetMapping("/getPlaylistByUserId")
    public List<PlaylistByUserIdResponse> getPlaylistByUserId(@RequestParam int userId) {
        return playlistService.getPlaylistByUserId(userId);
    }

    @GetMapping("/getPlaylistByUserIdAddMusic")
    public List<PlaylistByUserIdResponse> getPlaylistByUserIdAddMusic(@RequestParam int userId) {
        return playlistService.getPlaylistByUserIdAddMusic(userId);
    }
    // @PostMapping("/createPlaylist")
    // public ResponseEntity<?> createPlaylist(@RequestBody CreatePlaylistRequest
    // createPlaylistRequest) {
    // playlistService.createPlaylist(createPlaylistRequest);
    // return ResponseEntity.ok("them thanh cong");
    // }

    @PostMapping(value = "/createPlaylist", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPlaylist(
            @RequestParam("playlist_name") String playlistName,
            @RequestParam("user_id") Long userId,
            @RequestParam("image") MultipartFile image) {
        try {
            Playlist playlist = new Playlist();
            Optional<User> optionalUser = userRepository.findById(userId);
            if (!optionalUser.isPresent()) {
                return ResponseEntity.badRequest().body("User not found");
            }
            User user = optionalUser.get();
            playlist.setUser(user);
            playlist.setPlaylistName(playlistName);

            // Lưu trữ dữ liệu binary (hình ảnh) vào playlist
            playlist.setImg(image.getBytes()); // Lưu dưới dạng byte[].

            playlistRepository.save(playlist);
            return ResponseEntity.ok("Playlist created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating playlist");
        }
    }

}
