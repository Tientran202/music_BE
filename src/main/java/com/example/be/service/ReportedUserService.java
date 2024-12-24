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
                        (String) result[0], // userName
                        (int) result[1], // iuserIdd
                        (String) result[2], // reportUserName
                        (int) result[3], // annunciatorId
                        (String) result[4], // reportContent
                        (Date) result[5] // date
                )).collect(Collectors.toList());

                
    }

    public List<HiddenUserResponse> getAllHiddenUsers() {

        List<Object[]> queryResults = reportedUserRepository.findAllHiddenUsers();
        return queryResults.stream()
                .map(result -> new HiddenUserResponse(
                        (String) result[0], // userName
                        (int) result[1], // iuserIdd
                        (String) result[2], // reportUserName
                        (int) result[3], // annunciatorId
                        (Date) result[4], // date
                        (String) result[5], // reportContent
                        (Date) result[6] // date
                )).collect(Collectors.toList());   
    }

    

}
