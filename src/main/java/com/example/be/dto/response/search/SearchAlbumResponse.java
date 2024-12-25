package com.example.be.dto.response.search;

import java.util.Date;

public class SearchAlbumResponse {
    private int album_id;
    private String album_name;
    private Date replease_day;
    private byte[] img;
    private int artist_id;
    private String artist_name;

    public SearchAlbumResponse() {
    }

   

    public SearchAlbumResponse(int album_id, String album_name, Date replease_day, byte[] img, int artist_id,
            String artist_name) {
        this.album_id = album_id;
        this.album_name = album_name;
        this.replease_day = replease_day;
        this.img = img;
        this.artist_id = artist_id;
        this.artist_name = artist_name;
    }



    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public Date getReplease_day() {
        return replease_day;
    }

    public void setReplease_day(Date replease_day) {
        this.replease_day = replease_day;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }



    public byte[] getImg() {
        return img;
    }



    public void setImg(byte[] img) {
        this.img = img;
    }
}
