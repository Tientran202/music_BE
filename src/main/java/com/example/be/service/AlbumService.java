package com.example.be.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.be.dto.response.home.AlbumTopResponse;
import com.example.be.dto.response.indexAlbumPage.IndexAlbumResponse;
import com.example.be.dto.response.indexArtist.AlbumByArtistIdResponse;
import com.example.be.dto.response.search.SearchAlbumResponse;
import com.example.be.repository.AlbumRepository;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    private static final int SIMILARITY_THRESHOLD_HIGH = 50;
    private static final int SIMILARITY_THRESHOLD_LOW = 10;

    public List<AlbumTopResponse> getTopAlbum() {
        List<Object[]> queryResults = albumRepository.getTopAlbum();
        // Chuyển đổi dữ liệu từ query result sang DTO
        return queryResults.stream()
                .map(result -> new AlbumTopResponse(
                        (int) result[0], // musicId
                        (String) result[1], // genre
                        (byte[]) result[2] // musicImg
                )).collect(Collectors.toList());
    }

    public List<SearchAlbumResponse> searchAlbum(String keyword) {
        // Lấy toàn bộ danh sách bài hát
        List<Object[]> queryResults = albumRepository.getSearchAlbum();
        List<SearchAlbumResponse> albums = queryResults.stream()
                .map(result -> new SearchAlbumResponse(
                        (int) result[0],
                        (String) result[1],
                        (Date) result[2],
                        (byte[]) result[3],
                        (int) result[4],
                        (String) result[5]))
                .collect(Collectors.toList());

        // Lọc các bài hát có độ tương đồng trên 80%
        List<SearchAlbumResponse> highSimilarityAlbums = albums.stream()
                .filter(album -> isSimilar(album.getAlbum_name(), keyword, SIMILARITY_THRESHOLD_HIGH)) // 80%
                .sorted((m1, m2) -> Integer.compare(similarity(m2.getAlbum_name(), keyword),
                        similarity(m1.getAlbum_name(), keyword))) // Sắp xếp theo độ tương đồng giảm dần
                .collect(Collectors.toList());

        // Nếu không có kết quả với độ tương đồng trên 80%, tìm kiếm lại với độ tương
        // đồng trên 10%
        if (highSimilarityAlbums.isEmpty()) {
            highSimilarityAlbums = albums.stream()
                    .filter(music -> isSimilar(music.getAlbum_name(), keyword, SIMILARITY_THRESHOLD_LOW)) // Kiểm tra độ
                                                                                                          // tương đồng
                                                                                                          // trên 10%
                    .sorted((m1, m2) -> Integer.compare(similarity(m2.getAlbum_name(), keyword),
                            similarity(m1.getAlbum_name(), keyword))) // Sắp xếp theo độ tương đồng giảm dần
                    .collect(Collectors.toList());
            if (highSimilarityAlbums.isEmpty()) {
                return albums;
            }
        }
        return highSimilarityAlbums;
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

    public IndexAlbumResponse getIndexAlbum(int albumId) {
        List<Object[]> queryResults = albumRepository.getIndexAlbum(albumId);

        Object[] data = queryResults.get(0);

        IndexAlbumResponse response = new IndexAlbumResponse();
        response.setId((int) data[0]);
        response.setAlbum_name((String) data[1]);
        response.setArtist_name((String) data[2]);
        response.setReplease_day((Date) data[3]);
        response.setImg((byte[]) data[4]);
        return response;
    }

    public List<AlbumByArtistIdResponse> getAlbumByArtistId(int artistId) {
        List<Object[]> queryResults = albumRepository.getAlbumByArtistId(artistId);
        return queryResults.stream()
                .map(result -> new AlbumByArtistIdResponse(
                        (int) result[0], // musicId
                        (String) result[1], // musicImg
                        (byte[]) result[2] // genre
                )).collect(Collectors.toList());
    }

}
