package com.example.be.dto.response;

public class GenreMusicRespone {
    private int musicId;
    private String genre;
    private byte[] musicImg;
    private String musicName;
    private String userName;

    public String getGenre() {
        return genre;
    }

    public GenreMusicRespone(int musicId, String genre, byte[] musicImg, String musicName, String userName) {
        this.musicId = musicId;
        this.genre = genre;
        this.musicImg = musicImg;
        this.musicName = musicName;
        this.userName = userName;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public byte[] getMusicImg() {
        return musicImg;
    }

    public void setMusicImg(byte[] musicImg) {
        this.musicImg = musicImg;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicID) {
        this.musicId = musicID;
    }
}
