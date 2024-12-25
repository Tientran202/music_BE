package com.example.be.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.be.dto.response.indexArtist.PlaylistByArtistIdResponse;
import com.example.be.dto.response.indexUser.PlaylistByUserIdResponse;
import com.example.be.dto.response.search.SearchPlaylistResponse;
import com.example.be.repository.PlaylistRepository;

@Service
public class PlaylistService {
    @Autowired
    PlaylistRepository playlistRepository;
    private static final int SIMILARITY_THRESHOLD_HIGH = 50;
    private static final int SIMILARITY_THRESHOLD_LOW = 10;

    public List<SearchPlaylistResponse> searchPlaylist(String keyword) {
        // Lấy toàn bộ danh sách bài hát
        List<Object[]> queryResults = playlistRepository.getSearchPlayList();
        List<SearchPlaylistResponse> playlists = queryResults.stream()
                .map(result -> new SearchPlaylistResponse(
                        (int) result[0],
                        (String) result[1],
                        (byte[]) result[2],
                        (String) result[3],
                        (int) result[4]))
                .collect(Collectors.toList());

        // Lọc các bài hát có độ tương đồng trên 80%
        List<SearchPlaylistResponse> highSimilarityMusics = playlists.stream()
                .filter(playlist -> isSimilar(playlist.getPlaylist_name(), keyword, SIMILARITY_THRESHOLD_HIGH)) // 80%
                .sorted((m1, m2) -> Integer.compare(similarity(m2.getPlaylist_name(), keyword),
                        similarity(m1.getPlaylist_name(), keyword))) // Sắp xếp theo độ tương đồng giảm dần
                .collect(Collectors.toList());

        // Nếu không có kết quả với độ tương đồng trên 80%, tìm kiếm lại với độ tương
        // đồng trên 10%
        if (highSimilarityMusics.isEmpty()) {
            highSimilarityMusics = playlists.stream()
                    .filter(playlist -> isSimilar(playlist.getPlaylist_name(), keyword, SIMILARITY_THRESHOLD_LOW)) // Kiểm
                                                                                                                   // tra
                                                                                                                   // độ
                    // tương đồng
                    // trên 10%
                    .sorted((m1, m2) -> Integer.compare(similarity(m2.getPlaylist_name(), keyword),
                            similarity(m1.getPlaylist_name(), keyword))) // Sắp xếp theo độ tương đồng giảm dần
                    .collect(Collectors.toList());
            if (highSimilarityMusics.isEmpty()) {
                return playlists;
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

    public List<PlaylistByArtistIdResponse> getPlaylistByArtistId(int artistId) {
        List<Object[]> queryResults = playlistRepository.getPlaylistByArtistId(artistId);
        return queryResults.stream()
                .map(result -> new PlaylistByArtistIdResponse(
                        (int) result[0], // musicId
                        (String) result[1], // musicImg
                        (byte[]) result[2] // genre
                )).collect(Collectors.toList());
    }


    public List<PlaylistByUserIdResponse> getPlaylistByUserId(int userId) {
        List<Object[]> queryResults = playlistRepository.getPlaylistByUserId(userId);
        return queryResults.stream()
                .map(result -> new PlaylistByUserIdResponse(
                        (int) result[0], // musicId
                        (String) result[1], // musicImg
                        (byte[]) result[2] // genre
                )).collect(Collectors.toList());
    }
}