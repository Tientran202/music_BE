package com.example.be.dto.request;

public class updatePassResquest {
    private int account_id;
    private String oldPass;
    private String newPass;

    public updatePassResquest() {
    }

    public updatePassResquest(int account_id, String oldPass, String newPass) {
        this.account_id = account_id;
        this.oldPass = oldPass;
        this.newPass = newPass;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

}
