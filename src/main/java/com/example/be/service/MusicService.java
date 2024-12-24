package com.example.be.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.be.dto.response.GenreMusicRespone;
import com.example.be.dto.response.IndexMusicRespone;
import com.example.be.dto.response.home.NewMusicResponse;
import com.example.be.dto.response.search.SearchMusicResponse;
import com.example.be.model.Music;
import com.example.be.repository.MusicRepository;

@Service
public class MusicService {
    @Autowired
    private MusicRepository musicRepository;

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

    public IndexMusicRespone getIndexMusicByMusicId(int musicId) {
        List<Object[]> queryResults = musicRepository.findIndexMusicByMusicId(musicId);

        Object[] data = queryResults.get(0);

        IndexMusicRespone response = new IndexMusicRespone(null, null, null, null, null, null, null);

        response.setMusicAudio((byte[]) data[0]);
        response.setMusicImg((byte[]) data[1]);
        response.setDuration((BigDecimal) data[2]);
        response.setLyrics((String) data[3]);
        response.setMusic_name((String) data[4]);
        response.setArtist_name((String) data[5]);
        response.setStage_name((String) data[6]);
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
                        (BigDecimal) result[4]
                )).collect(Collectors.toList());

        // Lọc các bài hát có độ tương đồng trên 80%
        List<SearchMusicResponse> highSimilarityMusics = musics.stream()
                .filter(music -> isSimilar(music.getMusicName(), keyword, SIMILARITY_THRESHOLD_HIGH)) // 80%
                .sorted((m1, m2) -> Integer.compare(similarity(m2.getMusicName(), keyword),
                        similarity(m1.getMusicName(), keyword))) // Sắp xếp theo độ tương đồng giảm dần
                .collect(Collectors.toList());

        // Nếu không có kết quả với độ tương đồng trên 80%, tìm kiếm lại với độ tương
        // đồng trên 10%
        if (highSimilarityMusics.isEmpty()) {
            return musics.stream()
                    .filter(music -> isSimilar(music.getMusicName(), keyword, SIMILARITY_THRESHOLD_LOW)) // Kiểm tra độ
                                                                                                         // tương đồng
                                                                                                         // trên 10%
                    .sorted((m1, m2) -> Integer.compare(similarity(m2.getMusicName(), keyword),
                            similarity(m1.getMusicName(), keyword))) // Sắp xếp theo độ tương đồng giảm dần
                    .collect(Collectors.toList());
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
}
