package com.example.be.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.be.dto.response.admin.ListArtistResponse;
import com.example.be.dto.response.admin.ListUserResponse;
import com.example.be.dto.response.admin.RequestArtistResponse;
import com.example.be.dto.response.home.PopularArtistResponse;
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

}
