package com.example.be.dto.response.indexUser;

public class IndexUserResponse {
    private int user_id;
    private String user_name;
    private byte[] user_img;
    private Long total_playlist;
    public IndexUserResponse() {
    }
    public IndexUserResponse(int user_id, String user_name, byte[] user_img, Long total_playlist) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_img = user_img;
        this.total_playlist = total_playlist;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public byte[] getUser_img() {
        return user_img;
    }
    public void setUser_img(byte[] user_img) {
        this.user_img = user_img;
    }
    public Long getTotal_playlist() {
        return total_playlist;
    }
    public void setTotal_playlist(Long total_playlist) {
        this.total_playlist = total_playlist;
    }
    
}
