package com.example.be.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.be.dto.request.LoginRequestDTO;
import com.example.be.dto.response.home.IdRoleResponse;
import com.example.be.model.Account;
import com.example.be.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // public boolean login(LoginReuestDTO login) {
    // Optional<Account> optionalAccount =
    // accountRepository.findByEmail(login.getEmail());
    // if(optionalAccount){
    // if(optionalAccount.)
    // }
    // return false;
    // }

    public void CreateUseClone(String email, String hashedPassword) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(hashedPassword);
        account.setConfirmation(false);
        account.setRegister_date();
        accountRepository.save(account);
    }

    public void testAPI() {
        Account account = new Account();
        account.setConfirmation(false);
        accountRepository.save(account);
    }

    public boolean confirmationEmailByPass(String email, String pass) {
        Optional<Account> optionalAccount = accountRepository.findByEmail(email);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            return account.getPassword().equals(pass);
        }
        return false;
    }

    public IdRoleResponse getIdAccountByUserName(String username) {
        List<Object[]> idRoleResponse = accountRepository.getIdAccountByUserName(username);
        Object[] data = idRoleResponse.get(0);
        IdRoleResponse response = new IdRoleResponse();
        response.setAccount_id((int) data[0]);
        response.setRole((String) data[1]);
        return response;
        
    }

    public void register(String usename, String email) {
        Optional<Account> optionalAccount = accountRepository.findByEmail(email);
        Account account = optionalAccount.get();
        account.setUsername(usename);
        account.setConfirmation(true);
        accountRepository.save(account);
    }

    public boolean login(LoginRequestDTO loginRequestDTO) {
        Optional<Account> accountFindEmailOptional = accountRepository.findByEmail(loginRequestDTO.getUsername());
        if (accountFindEmailOptional.isPresent()) {
            Account account = accountFindEmailOptional.get();
            return passwordEncoder.matches(loginRequestDTO.getPassword(), account.getPassword());
        } else {
            Optional<Account> accountOptional = accountRepository.findByUsername(loginRequestDTO.getUsername());
            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();
                return passwordEncoder.matches(loginRequestDTO.getPassword(), account.getPassword());
            }
        }
        return false;
    }

    // public Optional<Account> findAccountByInfo(String info) {
    // Optional<Account> optionalAccount = accountRepository.findByEmail(info);
    // if (!optionalAccount.isPresent()) {
    // optionalAccount = accountRepository.findByUsername(info);
    // }
    // return optionalAccount;
    // }

    public void updateAccessTokenAndRefreshToken(String info, String accessToken, String refreshToken) {
        Optional<Account> optionalAccount = accountRepository.findAccountByInfo(info);
        Account account = optionalAccount.get();
        account.setAccess_token(accessToken);
        account.setRefresh_token(refreshToken);
        accountRepository.save(account);
    }

    public void updateAccessToken(String info, String accessToken) {
        Optional<Account> optionalAccount = accountRepository.findAccountByInfo(info);
        Account account = optionalAccount.get();
        account.setAccess_token(accessToken);
        accountRepository.save(account);
    }

    public void updateRefreshToken(String info, String refreshToken) {
        Optional<Account> optionalAccount = accountRepository.findAccountByInfo(info);
        Account account = optionalAccount.get();
        account.setRefresh_token(refreshToken);
        accountRepository.save(account);
    }

    public boolean authenticated(String email) {
        Optional<Account> optionalAccount = accountRepository.findByEmail(email);
        Account account = optionalAccount.get();
        if (account.isConfirmation() == false) {
            return false;
        }
        return true;
    }

}
