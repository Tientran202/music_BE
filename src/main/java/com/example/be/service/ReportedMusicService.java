package com.example.be.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.be.dto.response.admin.HiddenMusicResponse;
import com.example.be.dto.response.admin.ReportedMusicResponse;
import com.example.be.repository.ReportedMusicRepository;

@Service
public class ReportedMusicService {
    @Autowired
    ReportedMusicRepository reportedMusicRepository;

    public List<ReportedMusicResponse> getAllRepostedMusic() {

        List<Object[]> queryResults = reportedMusicRepository.findAllReportedMuisc();
        return queryResults.stream()
                .map(result -> new ReportedMusicResponse(
                        (String) result[0], // userName
                        (String) result[1], // iuserIdd
                        (String) result[2], // reportUserName
                        (Date) result[3], // reportContent
                        (String) result[4] // date
                )).collect(Collectors.toList());
    }

    public List<HiddenMusicResponse> getAllHiddenMusic() {

        List<Object[]> queryResults = reportedMusicRepository.findAllHiddenMuisc();
        return queryResults.stream()
                .map(result -> new HiddenMusicResponse(
                        (String) result[0], // userName
                        (String) result[1], // iuserIdd
                        (String) result[2], // reportUserName
                        (Date) result[3], // reportContent
                        (String) result[4], // date
                        (Date) result[5] // reportContent
                )).collect(Collectors.toList());
    }
}
