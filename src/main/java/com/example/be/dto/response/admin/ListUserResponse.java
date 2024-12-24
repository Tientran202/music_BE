package com.example.be.dto.response.admin;

import java.util.Date;

public class ListUserResponse {
    private int id;
    private String name;
    private Date register_date;
    public ListUserResponse() {
    }
    public ListUserResponse(int id, String name, Date register_date) {
        this.id = id;
        this.name = name;
        this.register_date = register_date;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getRegister_date() {
        return register_date;
    }
    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }
    
    
}
