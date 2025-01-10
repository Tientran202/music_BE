package com.example.be.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.be.dto.response.GenreMusicRespone;
import com.example.be.dto.response.admin.AllMusicUnconfirmedResponse;
import com.example.be.dto.response.artist.MusicByArtistIdResponse;
import com.example.be.dto.response.home.NewMusicResponse;
import com.example.be.dto.response.indexAlbumPage.MusicByAlbumIdResponse;
import com.example.be.dto.response.indexMusic.IndexMusicResponse;
import com.example.be.dto.response.indexMusic.SuggestedMusicResponse;
import com.example.be.dto.response.search.SearchMusicResponse;
import com.example.be.model.Music;
import com.example.be.model.ReportMusic;
import com.example.be.repository.MusicRepository;
import com.example.be.repository.ReportedMusicRepository;

@Service
public class MusicService {
        @Autowired
        private MusicRepository musicRepository;

        @Autowired
        private ReportedMusicRepository reportedMusicRepository;

        private static final int SIMILARITY_THRESHOLD_HIGH = 50;
        private static final int SIMILARITY_THRESHOLD_LOW = 10;

        public Optional<Music> getMusicById(int id) {
                return musicRepository.findById(id);
        }

        public List<GenreMusicRespone> getMusicByGenreId(int genreId) {
                List<Object[]> queryResults = musicRepository.findMusicByGenreId(genreId);

                // Chuyển đổi dữ liệu từ query result sang DTO
                return queryResults.stream()
                                .map(result -> new GenreMusicRespone(
                                                (int) result[0], // musicId
                                                (String) result[1], // genre
                                                (byte[]) result[2], // musicImg
                                                (String) result[3], // musicName
                                                (String) result[4]) // userName
                                ).collect(Collectors.toList());
        }

        public IndexMusicResponse getIndexMusicByMusicId(int musicId) {
                List<Object[]> queryResults = musicRepository.findIndexMusicByMusicId(musicId);
                Object[] data = queryResults.get(0);
                IndexMusicResponse response = new IndexMusicResponse();
                response.setMusicAudio((byte[]) data[0]);
                response.setMusicImg((byte[]) data[1]);
                response.setDuration((BigDecimal) data[2]);
                response.setLyrics((String) data[3]);
                response.setMusic_name((String) data[4]);
                response.setArtist_name((String) data[5]);
                response.setStage_name((String) data[6]);
                response.setArtist_id((int) data[7]);

                return response;
        }

        public IndexMusicResponse getIndexMusicByMusicIdForAdmin(int musicId) {
                List<Object[]> queryResults = musicRepository.findIndexMusicByMusicIdForAdmin(musicId);
                Object[] data = queryResults.get(0);
                IndexMusicResponse response = new IndexMusicResponse();
                response.setMusicAudio((byte[]) data[0]);
                response.setMusicImg((byte[]) data[1]);
                response.setDuration((BigDecimal) data[2]);
                response.setLyrics((String) data[3]);
                response.setMusic_name((String) data[4]);
                response.setArtist_name((String) data[5]);
                response.setStage_name((String) data[6]);
                response.setArtist_id((int) data[7]);
                return response;
        }

        public List<NewMusicResponse> getNewMusic() {
                List<Object[]> queryResults = musicRepository.getNewMusic();
                // Chuyển đổi dữ liệu từ query result sang DTO
                return queryResults.stream()
                                .map(result -> new NewMusicResponse(
                                                (int) result[0], // musicId
                                                (String) result[1], // genre
                                                (byte[]) result[2]// musicImg
                                )).collect(Collectors.toList());
        }

        public List<SearchMusicResponse> searchMusic(String keyword) {
                // Lấy toàn bộ danh sách bài hát
                List<Object[]> queryResults = musicRepository.getSearchMusic();
                List<SearchMusicResponse> musics = queryResults.stream()
                                .map(result -> new SearchMusicResponse(
                                                (int) result[0],
                                                (String) result[1],
                                                (byte[]) result[2],
                                                (String) result[3],
                                                (BigDecimal) result[4]))
                                .collect(Collectors.toList());

                // Lọc các bài hát có độ tương đồng trên 80%
                List<SearchMusicResponse> highSimilarityMusics = musics.stream()
                                .filter(music -> isSimilar(music.getMusicName(), keyword, SIMILARITY_THRESHOLD_HIGH)) // 80%
                                .sorted((m1, m2) -> Integer.compare(similarity(m2.getMusicName(), keyword),
                                                similarity(m1.getMusicName(), keyword))) // Sắp xếp theo độ tương đồng
                                                                                         // giảm dần
                                .collect(Collectors.toList());

                // Nếu không có kết quả với độ tương đồng trên 80%, tìm kiếm lại với độ tương
                // đồng trên 10%
                if (highSimilarityMusics.isEmpty()) {
                        highSimilarityMusics = musics.stream()
                                        .filter(music -> isSimilar(music.getMusicName(), keyword,
                                                        SIMILARITY_THRESHOLD_LOW)) // Kiểm tra độ
                                                                                   // tương đồng
                                                                                   // trên 10%
                                        .sorted((m1, m2) -> Integer.compare(similarity(m2.getMusicName(), keyword),
                                                        similarity(m1.getMusicName(), keyword))) // Sắp xếp theo độ
                                                                                                 // tương đồng giảm dần
                                        .collect(Collectors.toList());
                        if (highSimilarityMusics.isEmpty()) {
                                return musics;
                        }
                }

                // Trả về kết quả tìm kiếm với độ tương đồng trên 80% nếu có
                return highSimilarityMusics;
        }

        private boolean isSimilar(String text, String keyword, int threshold) {
                // Kiểm tra độ tương đồng với từ khóa có ngưỡng xác định
                return similarity(text, keyword) >= threshold;
        }

        private int similarity(String text, String keyword) {
                LevenshteinDistance distance = new LevenshteinDistance();
                int maxLen = Math.max(text.length(), keyword.length());
                if (maxLen == 0)
                        return 100;
                int diff = distance.apply(text.toLowerCase(), keyword.toLowerCase());
                return (int) (((double) (maxLen - diff) / maxLen) * 100);
        }

        public List<MusicByAlbumIdResponse> getMusicByAlbum(int albumId) {
                List<Object[]> queryResults = musicRepository.getMusicByAlbum(albumId);
                // Chuyển đổi dữ liệu từ query result sang DTO
                return queryResults.stream()
                                .map(result -> new MusicByAlbumIdResponse(
                                                (int) result[0], // musicId
                                                (String) result[1], // genre
                                                (byte[]) result[2], // musicImg
                                                (BigDecimal) result[3], // musicImg
                                                (byte[]) result[4] // musicImg
                                )).collect(Collectors.toList());
        }

        public List<MusicByAlbumIdResponse> getMusicByPlaylistId(int playlistId) {
                List<Object[]> queryResults = musicRepository.getMusicByPlaylistId(playlistId);
                // Chuyển đổi dữ liệu từ query result sang DTO
                return queryResults.stream()
                                .map(result -> new MusicByAlbumIdResponse(
                                                (int) result[0],
                                                (String) result[1],
                                                (byte[]) result[2],
                                                (BigDecimal) result[3],
                                                (byte[]) result[4]))
                                .collect(Collectors.toList());
        }

        public List<SuggestedMusicResponse> getSuggestedMusicResponse(int artistId) {
                List<Object[]> queryResults = musicRepository.getSuggestedMusicByArtistId(artistId);
                // Chuyển đổi dữ liệu từ query result sang DTO
                return queryResults.stream()
                                .map(result -> new SuggestedMusicResponse(
                                                (int) result[0], // musicId
                                                (String) result[1], // genre
                                                (byte[]) result[2] // musicImg
                                )).collect(Collectors.toList());
        }

        public List<SuggestedMusicResponse> getSuggestedMusicForAdminResponse(int artistId) {
                List<Object[]> queryResults = musicRepository.getSuggestedMusicForAdminResponse(artistId);
                // Chuyển đổi dữ liệu từ query result sang DTO
                return queryResults.stream()
                                .map(result -> new SuggestedMusicResponse(
                                                (int) result[0], // musicId
                                                (String) result[1], // genre
                                                (byte[]) result[2] // musicImg
                                )).collect(Collectors.toList());
        }

        public List<Music> findAllForAlbumByArtistId(int artistId) {
                List<Object[]> queryResults = musicRepository.findAllForAlbumByArtistId(artistId);
                return queryResults.stream()
                                .map(result -> new Music(
                                                (int) result[0], // musicId
                                                (String) result[1] // genre
                                )).collect(Collectors.toList());
        }

        public Music saveStory(String title, MultipartFile audioFile) throws Exception {
                byte[] audioData = audioFile.getBytes();
                Music music = new Music(title, audioData);
                return musicRepository.save(music);
        }

        public List<MusicByArtistIdResponse> getMusicByArtistId(int artistId) {
                List<Object[]> queryResults = musicRepository.getMusicByArtistId(artistId);
                return queryResults.stream()
                                .map(result -> new MusicByArtistIdResponse(
                                                (int) result[0], // musicId
                                                (String) result[1], // genre
                                                (byte[]) result[2] // genre
                                )).collect(Collectors.toList());
        }

        public List<MusicByArtistIdResponse> getAllMusicCByArtistId(int artistId) {
                List<Object[]> queryResults = musicRepository.getAllMusicCByArtistId(artistId);
                return queryResults.stream()
                                .map(result -> new MusicByArtistIdResponse(
                                                (int) result[0], // musicId
                                                (String) result[1], // genre
                                                (byte[]) result[2], // genre
                                                (boolean) result[3], // genre
                                                (boolean) result[4] // genre
                                )).collect(Collectors.toList());
        }

        public List<MusicByArtistIdResponse> getMusicByArtistIdForCreateAlbum(int artistId) {
                List<Object[]> queryResults = musicRepository.getMusicByArtistIdForCreateAlbum(artistId);
                return queryResults.stream()
                                .map(result -> new MusicByArtistIdResponse(
                                                (int) result[0], // musicId
                                                (String) result[1], // genre
                                                (byte[]) result[2] // genre
                                )).collect(Collectors.toList());
        }

        public List<MusicByArtistIdResponse> getAllMusicByArtistId(int artistId) {
                List<Object[]> queryResults = musicRepository.getAllMusicByArtistId(artistId);
                return queryResults.stream()
                                .map(result -> new MusicByArtistIdResponse(
                                                (int) result[0], // musicId
                                                (String) result[1], // genre
                                                (byte[]) result[2] // genre
                                )).collect(Collectors.toList());
        }

        public List<AllMusicUnconfirmedResponse> getAllMusicUnconfirmed() {
                List<Object[]> queryResults = musicRepository.getAllMusicUnconfirmed();
                return queryResults.stream()
                                .map(result -> new AllMusicUnconfirmedResponse(
                                                (int) result[0], // musicId
                                                (String) result[1], // genre
                                                (String) result[2] // genre
                                )).collect(Collectors.toList());
        }

        public void confirmationMusicById(int musicId) {
                Optional<Music> musOptional = musicRepository.findById(musicId);
                Music music = musOptional.get();
                music.setConfirmation(true);
                musicRepository.save(music);
        }

        public Long getNumberOfMusic() {
                return musicRepository.getNumberOfMusic();
        }

        public void hiddenReportMusic(int reportId) {
                Optional<ReportMusic> reportMusicOptional = reportedMusicRepository.findById(reportId);
                ReportMusic reportMusic = reportMusicOptional.get();
                reportMusic.setHidden(true);
                reportMusic.setHidden_time();
                reportedMusicRepository.save(reportMusic);
        }

        public void hiddenMusic(int reportId) {
                Optional<ReportMusic> reportMusicOptional = reportedMusicRepository.findById(reportId);
                ReportMusic reportMusic = reportMusicOptional.get();
                reportMusic.setHidden(true);
                reportedMusicRepository.save(reportMusic);

                Music music = reportMusic.getMusic();
                music.setHidden(true);
                musicRepository.save(music);
        }

        public void cancelHiddenMusic(int musicId) {
                Optional<Music> musicOptional = musicRepository.findById(musicId);
                Music music = musicOptional.get();
                music.setHidden(false);
                musicRepository.save(music);
        }

        

}
