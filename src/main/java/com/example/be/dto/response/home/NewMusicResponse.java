package com.example.be.dto.response.home;

public class NewMusicResponse {
    private int id;
    private String name_music;
    private byte[] img;
    public NewMusicResponse() {
    }
    public NewMusicResponse(int id, String name_music, byte[] img) {
        this.id = id;
        this.name_music = name_music;
        this.img = img;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName_music() {
        return name_music;
    }
    public void setName_music(String name_music) {
        this.name_music = name_music;
    }
    public byte[] getImg() {
        return img;
    }
    public void setImg(byte[] img) {
        this.img = img;
    }
    
}
