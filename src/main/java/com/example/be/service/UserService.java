package com.example.be.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.be.dto.response.admin.ListArtistResponse;
import com.example.be.dto.response.admin.ListUserResponse;
import com.example.be.dto.response.admin.RequestArtistResponse;
import com.example.be.dto.response.home.PopularArtistResponse;
import com.example.be.dto.response.indexArtist.IndexArtistResponse;
import com.example.be.dto.response.indexUser.FlowingByUserIdResponse;
import com.example.be.dto.response.indexUser.IndexUserResponse;
import com.example.be.dto.response.search.SearchArtistResponse;
import com.example.be.model.Account;
import com.example.be.model.User;
import com.example.be.repository.AccountRepository;
import com.example.be.repository.UserRepository;

@Service
public class UserService {
        @Autowired
        private AccountRepository accountRepository;

        @Autowired
        private UserRepository userRepository;

        private static final int SIMILARITY_THRESHOLD_HIGH = 50; // Ngưỡng 80% để tìm bài hát với độ tương đồng cao
        private static final int SIMILARITY_THRESHOLD_LOW = 10;

        public User getUserByUsername(String username) {
                Optional<Account> accountOptional = accountRepository.findByUsername(username);
                Account account = accountOptional.get();
                if (account.getUser() != null) {
                        return account.getUser();
                } else {
                        return null;
                }
        }

        public List<RequestArtistResponse> requestToBecomeArtist() {
                List<Object[]> queryResults = userRepository.findAllRequestToBecomeArtist();

                // Chuyển đổi dữ liệu từ query result sang DTO
                return queryResults.stream()
                                .map(result -> new RequestArtistResponse(
                                                (int) result[0], // musicId
                                                (String) result[1], // genre
                                                (String) result[2], // musicImg
                                                (Date) result[3] // musicName
                                )).collect(Collectors.toList());
        }

        public List<ListArtistResponse> findAllArtist() {
                List<Object[]> queryResults = userRepository.findAllArtist();

                // Chuyển đổi dữ liệu từ query result sang DTO
                return queryResults.stream()
                                .map(result -> new ListArtistResponse(
                                                (int) result[0], // musicId
                                                (String) result[1], // genre
                                                (Long) result[2], // musicImg
                                                (Long) result[3], // musicImg
                                                (Date) result[4] // musicName
                                )).collect(Collectors.toList());
        }

        public List<ListUserResponse> findAllUser() {
                List<Object[]> queryResults = userRepository.findAllUser();
                // Chuyển đổi dữ liệu từ query result sang DTO
                return queryResults.stream()
                                .map(result -> new ListUserResponse(
                                                (int) result[0], // musicId
                                                (String) result[1], // genre
                                                (Date) result[2] // musicName
                                )).collect(Collectors.toList());
        }

        public List<PopularArtistResponse> getPopularArtist(int id) {
                List<Object[]> queryResults = userRepository.getPopularArtist(id);
                return queryResults.stream()
                                .map(result -> new PopularArtistResponse(
                                                (int) result[0], // musicId
                                                (String) result[1], // genre
                                                (byte[]) result[2] // musicName
                                )).collect(Collectors.toList());
        }

        public List<SearchArtistResponse> searchArtist(String keyword) {
                // Lấy toàn bộ danh sách bài hát
                List<Object[]> queryResults = userRepository.getSearchArtist();
                List<SearchArtistResponse> artists = queryResults.stream()
                                .map(result -> new SearchArtistResponse(
                                                (int) result[0],
                                                (String) result[1],
                                                (byte[]) result[2]))
                                .collect(Collectors.toList());

                List<SearchArtistResponse> highSimilarityMusics = artists.stream()
                                .filter(artist -> isSimilar(artist.getName(), keyword, SIMILARITY_THRESHOLD_HIGH))
                                .sorted((m1, m2) -> Integer.compare(similarity(m2.getName(), keyword),
                                                similarity(m1.getName(), keyword)))
                                .collect(Collectors.toList());
                if (highSimilarityMusics.isEmpty()) {
                        highSimilarityMusics = artists.stream()
                                        .filter(music -> isSimilar(music.getName(), keyword, SIMILARITY_THRESHOLD_LOW))
                                        .sorted((m1, m2) -> Integer.compare(similarity(m2.getName(), keyword),
                                                        similarity(m1.getName(), keyword)))
                                        .collect(Collectors.toList());
                        if (highSimilarityMusics.isEmpty()) {
                                return artists;
                        }
                }
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

        public IndexArtistResponse getIndexArtist(int artistId) {
                List<Object[]> queryResults = userRepository.getIndexArtist(artistId);
                Object[] data = queryResults.get(0);
                IndexArtistResponse response = new IndexArtistResponse();
                response.setArtist_name((String) data[0]);
                response.setArtist_image((byte[]) data[1]);
                response.setAlbum_count((Long) data[2]);
                response.setPlaylist_count((Long) data[3]);
                response.setFlow_count((Long) data[4]);
                return response;
        }

        public IndexUserResponse getIndexUser(int userId) {
                List<Object[]> queryResults = userRepository.getIndexUser(userId);
                Object[] data = queryResults.get(0);
                IndexUserResponse response = new IndexUserResponse();
                response.setUser_id((int) data[0]);
                response.setUser_name((String) data[1]);
                response.setUser_img((byte[]) data[2]);
                response.setTotal_playlist((Long) data[3]);
                return response;
        }

        public List<FlowingByUserIdResponse> getFlowingByUserId(int userId) {
                List<Object[]> queryResults = userRepository.getFllowingByUserId(userId);

                // Chuyển đổi dữ liệu từ query result sang DTO
                return queryResults.stream()
                                .map(result -> new FlowingByUserIdResponse(
                                                (int) result[0], // musicId
                                                (String) result[1], // genre
                                                (byte[]) result[2] // musicImg
                                )).collect(Collectors.toList());
        }

        public String getRoleByUsername(String username) {
                List<String> queryResults = userRepository.getRoleByUsername(username);
                String data = queryResults.get(0);
                return data;
        }

}
