package com.example.be.dto.response.artist;

public class SelectMusicByArtistIdResponse {
    private int id;
    private String musicName;
    public SelectMusicByArtistIdResponse() {
    }
    public SelectMusicByArtistIdResponse(int id, String musicName) {
        this.id = id;
        this.musicName = musicName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMusicName() {
        return musicName;
    }
    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }
    
}
