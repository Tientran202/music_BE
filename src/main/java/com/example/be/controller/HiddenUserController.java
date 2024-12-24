package com.example.be.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.be.dto.response.admin.HiddenUserResponse;
import com.example.be.service.ReportedUserService;

@RestController
@RequestMapping("/api/admin")
public class HiddenUserController {
    
    @Autowired ReportedUserService reportedUserService;

    @GetMapping("hiddenUser")
    public List<HiddenUserResponse> HiddenUsers(){
        return reportedUserService.getAllHiddenUsers();
    }
}
