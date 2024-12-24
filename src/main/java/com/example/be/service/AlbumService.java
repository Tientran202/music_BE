package com.example.be.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.be.dto.response.home.AlbumTopResponse;
import com.example.be.repository.AlbumRepository;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository albumRepository;

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
}
