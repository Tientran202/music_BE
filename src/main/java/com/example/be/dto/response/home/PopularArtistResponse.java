package com.example.be.dto.response.home;

public class PopularArtistResponse {
    private int id;
    private String stage_name;
    private byte[] avatar;
    public PopularArtistResponse() {
    }
    public PopularArtistResponse(int id, String stage_name, byte[] avatar) {
        this.id = id;
        this.stage_name = stage_name;
        this.avatar = avatar;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getStage_name() {
        return stage_name;
    }
    public void setStage_name(String stage_name) {
        this.stage_name = stage_name;
    }
    public byte[] getAvatar() {
        return avatar;
    }
    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
    
}
