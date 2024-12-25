package com.example.be.dto.response.indexAlbumPage;

import java.util.Date;

public class IndexAlbumResponse {
    private int id;
    private String album_name;
    private String artist_name;
    private Date replease_day;
    private byte[] img;
    public IndexAlbumResponse() {
    }

    public IndexAlbumResponse(int id, String album_name, String artist_name, Date replease_day, byte[] img) {
        this.id = id;
        this.album_name = album_name;
        this.artist_name = artist_name;
        this.replease_day = replease_day;
        this.img = img;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAlbum_name() {
        return album_name;
    }
    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }
    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getArtist_name() {
        return artist_name;
    }
    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }
    public Date getReplease_day() {
        return replease_day;
    }
    public void setReplease_day(Date replease_day) {
        this.replease_day = replease_day;
    }

    
}
