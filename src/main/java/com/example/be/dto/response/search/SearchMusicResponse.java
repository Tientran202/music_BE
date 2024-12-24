package com.example.be.dto.response.search;

import java.math.BigDecimal;

public class SearchMusicResponse {
    private int id;
    private String musicName;
    private byte[] img;
    private  String name;
    private BigDecimal duration;

    public SearchMusicResponse() {
    }

    public SearchMusicResponse(int id, String musicName, byte[] img, String name, BigDecimal duration) {
        this.id = id;
        this.musicName = musicName;
        this.img = img;
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
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

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

}
