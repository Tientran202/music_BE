package com.example.be.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.be.dto.response.admin.HiddenMusicResponse;
import com.example.be.dto.response.admin.ReportedMusicResponse;
import com.example.be.repository.MusicRepository;
import com.example.be.repository.ReportedMusicRepository;

@Service
public class ReportedMusicService {
        @Autowired
        ReportedMusicRepository reportedMusicRepository;

        @Autowired
        MusicRepository musicRepository;

        public List<ReportedMusicResponse> getAllRepostedMusic() {

                List<Object[]> queryResults = reportedMusicRepository.findAllReportedMuisc();
                return queryResults.stream()
                                .map(result -> new ReportedMusicResponse(
                                                (int) result[0],
                                                (int) result[1],
                                                (String) result[2], 
                                                (String) result[3],
                                                (String) result[4], 
                                                (Date) result[5], 
                                                (String) result[6]
                                )).collect(Collectors.toList());
        }

        public List<HiddenMusicResponse> getAllHiddenMusic() {
                List<Object[]> queryResults = musicRepository.findAllHiddenMuisc();
                return queryResults.stream()
                                .map(result -> new HiddenMusicResponse(
                                                (int) result[0],
                                                (String) result[1], 
                                                (String) result[2],
                                                (String) result[3], 
                                                (Date) result[4], 
                                                (String) result[5],
                                                (Date) result[6]
                                )).collect(Collectors.toList());
        }
}
