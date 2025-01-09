package com.example.be.dto.request;

import java.util.Date;

public class RegisterUserRequest {

    private String username;
    private String password;
    private String email;
    private String name;
    private Date register_date;

    public RegisterUserRequest() {
    }

    public RegisterUserRequest(String username, String password, String email, String name, Date register_date) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.register_date = register_date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegister_date() {
        this.register_date = new Date();
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
