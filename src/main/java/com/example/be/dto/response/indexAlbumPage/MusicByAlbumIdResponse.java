package com.example.be.dto.response.indexAlbumPage;

import java.math.BigDecimal;

public class MusicByAlbumIdResponse {
    private int id;
    private String music_name;
    private byte[] img;
    private BigDecimal duration;
    private byte[] audio;


    public MusicByAlbumIdResponse() {
    }


    public MusicByAlbumIdResponse(int id, String music_name, byte[] img, BigDecimal duration, byte[] audio) {
        this.id = id;
        this.music_name = music_name;
        this.img = img;
        this.duration = duration;
        this.audio = audio;
    }


    public int getId() {
        return id;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
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


    public byte[] getAudio() {
        return audio;
    }


    public void setAudio(byte[] audio) {
        this.audio = audio;
    }

}
