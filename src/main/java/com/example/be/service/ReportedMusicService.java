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
                                                (String) result[2], // userName
                                                (String) result[3], // iuserIdd
                                                (String) result[4], // reportUserName
                                                (Date) result[5], // reportContent
                                                (String) result[6] // date
                                )).collect(Collectors.toList());
        }

        public List<HiddenMusicResponse> getAllHiddenMusic() {
                List<Object[]> queryResults = musicRepository.findAllHiddenMuisc();
                return queryResults.stream()
                                .map(result -> new HiddenMusicResponse(
                                                (int) result[0],
                                                (String) result[1], // userName
                                                (String) result[2], // iuserIdd
                                                (String) result[3], // reportUserName
                                                (Date) result[4], // reportContent
                                                (String) result[5], // date
                                                (Date) result[6] // reportContent
                                )).collect(Collectors.toList());
        }
}
