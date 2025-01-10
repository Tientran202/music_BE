package com.example.be.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.be.dto.response.admin.AllMusicUnconfirmedResponse;
import com.example.be.dto.response.artist.MusicByArtistIdResponse;
import com.example.be.dto.response.artist.SelectMusicByArtistIdResponse;
import com.example.be.dto.response.home.NewMusicResponse;
import com.example.be.dto.response.indexAlbumPage.MusicByAlbumIdResponse;
import com.example.be.dto.response.indexMusic.IndexMusicResponse;
import com.example.be.dto.response.indexMusic.SuggestedMusicResponse;
import com.example.be.dto.response.search.SearchMusicResponse;
import com.example.be.model.Album;
import com.example.be.model.Genre;
import com.example.be.model.Music;
import com.example.be.model.Playlist;
import com.example.be.model.PlaylistMusicRelation;
import com.example.be.model.ReportMusic;
import com.example.be.model.User;
import com.example.be.repository.AlbumRepository;
import com.example.be.repository.GenreRepository;
import com.example.be.repository.MusicRepository;
import com.example.be.repository.PlaylistMusicRelationRepository;
import com.example.be.repository.PlaylistRepository;
import com.example.be.repository.ReportedMusicRepository;
import com.example.be.repository.UserRepository;
import com.example.be.service.MusicService;

@RestController
@RequestMapping("/api/music")
public class MusicController {

    @Autowired
    private MusicService musicService;
    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistMusicRelationRepository playlistMusicRelationRepository;
    @Autowired
    private ReportedMusicRepository reportedMusicRepository;

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
        IndexMusicResponse indexMusic = musicService.getIndexMusicByMusicId(id);
        return ResponseEntity.ok(indexMusic);
    }

    @GetMapping("/getIndexMusicByMusicIdForAdmin/{id}")
    public ResponseEntity<?> getIndexMusicByMusicIdForAdmin(@PathVariable int id) {
        IndexMusicResponse indexMusic = musicService.getIndexMusicByMusicIdForAdmin(id);
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

    @GetMapping("/getMusicByAlbumId")
    public List<MusicByAlbumIdResponse> getMusicByAlbumId(@RequestParam int albumId) {
        return musicService.getMusicByAlbum(albumId);
    }

    @GetMapping("/getMusicByPlaylistId")
    public List<MusicByAlbumIdResponse> getMusicByPlaylistId(@RequestParam int playlistId) {
        return musicService.getMusicByPlaylistId(playlistId);
    }

    @GetMapping("/getSuggestedMusicResponse")
    public List<SuggestedMusicResponse> getSuggestedMusicResponse(@RequestParam int artistId) {
        return musicService.getSuggestedMusicResponse(artistId);
    }

    @GetMapping("/getSuggestedMusicForAdminResponse")
    public List<SuggestedMusicResponse> getSuggestedMusicForAdminResponse(@RequestParam int artistId) {
        return musicService.getSuggestedMusicForAdminResponse(artistId);
    }

    @PostMapping("/upload-music")
    public ResponseEntity<String> uploadMusic(@RequestParam("file") MultipartFile musicFile,
            @RequestParam("music_name") String musicName,
            @RequestParam("genreId") int genreId,
            @RequestParam("artist_id") int artist_id,
            @RequestParam(value = "img", required = false) MultipartFile imgFile) {

        try {
            // Lưu tệp âm thanh vào mảng byte
            byte[] audioBytes = musicFile.getBytes();

            // Lưu ảnh vào mảng byte nếu có ảnh
            byte[] imgBytes = null;
            if (imgFile != null) {
                imgBytes = imgFile.getBytes();
                System.out.println(imgBytes);
            }
            Optional<User> optionalUser = userRepository.findById(artist_id);
            User artist = optionalUser.get();
            Genre genre = genreRepository.findById(genreId);
            Music music = new Music();
            music.setGenre(genre);
            music.setMusicName(musicName);
            music.setAudio(audioBytes);
            music.setImg(imgBytes);
            music.setArtist(artist);
            // Lưu vào cơ sở dữ liệu
            musicRepository.save(music);
            return ResponseEntity.ok("Music uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();

            return ResponseEntity.status(500).body("Error uploading files.");
        }
    }

    @GetMapping("/artist-songs/{artistId}")
    public ResponseEntity<List<SelectMusicByArtistIdResponse>> getIdSongsByArtistId(@PathVariable int artistId) {
        List<Music> userSongs = musicRepository.findAllByArtistId(artistId);
        List<SelectMusicByArtistIdResponse> response = userSongs.stream()
                .map(song -> new SelectMusicByArtistIdResponse(song.getId(), song.getMusicName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/musicForAlbumByArtist/{artistId}")
    public ResponseEntity<List<Music>> getIdSongsForAlbumByArtistId(@PathVariable int artistId) {
        List<Music> musicForAlbumByArtist = musicService.findAllForAlbumByArtistId(artistId);
        return ResponseEntity.ok(musicForAlbumByArtist);
    }

    @GetMapping("/downloadAudioMusicByMusicId/{musicId}")
    public ResponseEntity<byte[]> downloadSong(@PathVariable Long musicId) {
        Music song = musicRepository.findById(musicId)
                .orElseThrow(() -> new RuntimeException("Music not found"));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + song.getMusicName() + ".mp3\"")
                .body(song.getAudio());
    }

    @GetMapping("getMusicByArtistId")
    public List<MusicByArtistIdResponse> getMusicByArtistId(@RequestParam int artistId) {
        return musicService.getMusicByArtistId(artistId);
    }

    @GetMapping("getAllMusicCByArtistId")
    public List<MusicByArtistIdResponse> getAllMusicCByArtistId(@RequestParam int artistId) {
        return musicService.getAllMusicCByArtistId(artistId);
    }

    @GetMapping("getNumberOfMusic")
    public Long getNumberOfMusic() {
        return musicService.getNumberOfMusic();
    }

    @GetMapping("getMusicByArtistIdForCreateAlbum")
    public List<MusicByArtistIdResponse> getMusicByArtistIdForCreateAlbum(@RequestParam int artistId) {
        return musicService.getMusicByArtistIdForCreateAlbum(artistId);
    }

    @GetMapping("getAllMusicByArtistId")
    public List<MusicByArtistIdResponse> getAllMusicByArtistId(@RequestParam int artistId) {
        return musicService.getAllMusicByArtistId(artistId);
    }

    @PatchMapping("/updateAlbumIdforMusic")
    public ResponseEntity<?> updateMusicAlbums(@RequestBody List<Map<String, Integer>> requests) {
        for (Map<String, Integer> request : requests) {
            int musicid = request.get("musicId");
            int albumId = request.get("albumId");
            Optional<Music> musicOptional = musicRepository.findById(musicid);
            Optional<Album> albumOptional = albumRepository.findById(albumId);

            if (musicOptional.isPresent()) {
                Music music = musicOptional.get();
                if (albumOptional.isPresent()) {
                    Album album = albumOptional.get();
                    // Gán album vào bài hát
                    music.setAlbum(album);
                    musicRepository.save(music); // Lưu lại bài hát đã cập nhật vào cơ sở dữ liệu
                } else {
                    // Trường hợp không tìm thấy album
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Album with id " + albumId + " not found.");
                }
            }
        }
        return ResponseEntity.ok("Album IDs updated successfully for all music.");
    }

    @PostMapping("/testupload")
    public ResponseEntity<String> uploadStory(
            @RequestParam("title") String title,
            @RequestParam("audio") MultipartFile audioFile) {
        try {
            musicService.saveStory(title, audioFile);
            return new ResponseEntity<>("File uploaded and saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving the file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getAllMusicUnconfirmed")
    public List<AllMusicUnconfirmedResponse> getAllMusicUnconfirmed() {
        return musicService.getAllMusicUnconfirmed();
    }

    @PatchMapping("confirmationMusicById")
    public ResponseEntity<?> confirmationMusicById(@RequestParam("musicId") int musicId) {
        musicService.confirmationMusicById(musicId);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/addToPlaylist")
    public ResponseEntity<?> addToPlaylist(@RequestParam("musicId") int musicId,
            @RequestParam("playlistId") int playlistId) {
        PlaylistMusicRelation playlistMusicRelation = new PlaylistMusicRelation();
        Playlist playlist = playlistRepository.findById(playlistId);
        Optional<Music> musicOptional = musicRepository.findById(musicId);
        Music music = musicOptional.get();
        playlistMusicRelation.setMusic(music);
        playlistMusicRelation.setPlaylist(playlist);
        playlistMusicRelationRepository.save(playlistMusicRelation);
        return ResponseEntity.ok("follow successfully");
    }

    @PostMapping("/reportMusic")
    public ResponseEntity<?> reportMusic(
            @RequestParam("report_content") String report_content,
            @RequestParam("announcer_id") int announcer_id,
            @RequestParam("musicId") int musicId) {
        ReportMusic reportMusic = new ReportMusic();
        Optional<User> userOptional = userRepository.findById(announcer_id);
        User user = userOptional.get();

        Optional<Music> musicOptional = musicRepository.findById(musicId);
        Music music = musicOptional.get();
        reportMusic.setMusic(music);
        reportMusic.setAnnouncer(user);
        reportMusic.setReportContent(report_content);
        reportMusic.setDay();
        reportedMusicRepository.save(reportMusic);
        return ResponseEntity.ok("repoet successfully");
    }

    //

    // @GetMapping("/cut")
    // public ResponseEntity<byte[]> cutMusic(@RequestParam int musicId,
    //         @RequestParam int startSeconds,
    //         @RequestParam int endSeconds) {
    //     try {
    //         byte[] cutAudio = musicService.cutMusic(musicId, startSeconds, endSeconds);
    //         return ResponseEntity.ok()
    //                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"cut_music.mp3\"")
    //                 .contentType(MediaType.parseMediaType("audio/mpeg"))
    //                 .body(cutAudio); // Trả về âm thanh đã cắt dưới dạng byte[]
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body(null);
    //     }
    // }

}
