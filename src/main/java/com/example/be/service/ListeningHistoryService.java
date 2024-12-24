package com.example.be.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.be.dto.response.home.RecentlyListenedMusicResponse;
import com.example.be.repository.ListeningHistoryRepository;

@Service
public class ListeningHistoryService {
    @Autowired
    ListeningHistoryRepository listeningHistoryRepository;

    public List<RecentlyListenedMusicResponse> getRecentlyMusic(int account) {
        List<Object[]> queryResults = listeningHistoryRepository.getListeningHistory(account);
        // Chuyển đổi dữ liệu từ query result sang DTO
        return queryResults.stream()
                .map(result -> new RecentlyListenedMusicResponse(
                        (int) result[0], // musicId
                        (String) result[1], // genre
                        (byte[]) result[2]// musicImg
                )).collect(Collectors.toList());
    }
}
