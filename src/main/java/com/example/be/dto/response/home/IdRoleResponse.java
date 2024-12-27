package com.example.be.dto.response.home;

public class IdRoleResponse {
    private int account_id;
    private String role;
    public IdRoleResponse() {
    }
    public IdRoleResponse(int account_id, String role) {
        this.account_id = account_id;
        this.role = role;
    }
    public int getAccount_id() {
        return account_id;
    }
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
}
