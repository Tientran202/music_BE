package com.example.be.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.be.dto.response.GenreMusicRespone;
import com.example.be.dto.response.IndexMusicRespone;
import com.example.be.dto.response.home.NewMusicResponse;
import com.example.be.model.Music;
import com.example.be.repository.MusicRepository;

@Service
public class MusicService {
    @Autowired
    private MusicRepository musicRepository;

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

}
