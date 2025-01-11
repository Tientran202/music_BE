package com.example.be.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.be.dto.response.admin.HiddenUserResponse;
import com.example.be.dto.response.admin.ReportedUserResponse;
import com.example.be.repository.ReportedUserRepository;

@Service
public class ReportedUserService {
    @Autowired
    private ReportedUserRepository reportedUserRepository;

    public List<ReportedUserResponse> getAllRepostedUser() {

        List<Object[]> queryResults = reportedUserRepository.findAllReportedUsers();
        return queryResults.stream()
                .map(result -> new ReportedUserResponse(
                        (String) result[0],
                        (int) result[1], 
                        (String) result[2],
                        (int) result[3], 
                        (String) result[4],
                        (Date) result[5] 
                )).collect(Collectors.toList());

                
    }

    public List<HiddenUserResponse> getAllHiddenUsers() {

        List<Object[]> queryResults = reportedUserRepository.findAllHiddenUsers();
        return queryResults.stream()
                .map(result -> new HiddenUserResponse(
                        (String) result[0], 
                        (int) result[1], 
                        (String) result[2], 
                        (int) result[3], 
                        (Date) result[4], 
                        (String) result[5], 
                        (Date) result[6] 
                )).collect(Collectors.toList());   
    }

    

}
