package com.example.be.dto.response.home;

public class RecentlyListenedMusicResponse {
    private int id;
    private String music_name;
    private byte[] img;
    public RecentlyListenedMusicResponse() {
    }
    public RecentlyListenedMusicResponse(int id, String music_name, byte[] img) {
        this.id = id;
        this.music_name = music_name;
        this.img = img;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMusic_name() {
        return music_name;
    }
    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }
    public byte[] getImg() {
        return img;
    }
    public void setImg(byte[] img) {
        this.img = img;
    }
    
}
