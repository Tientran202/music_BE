package com.example.be.dto.response.allAlbum;

import java.util.Date;

public class AllAlbumResponse {
    private int album_id;
    private String album_name;
    private byte[] album_img;
    private Date album_release_day;
    private int artist_id;
    private String artist_name;

    public AllAlbumResponse() {
    }

    public AllAlbumResponse(int album_id, String album_name, byte[] album_img, Date album_release_day, int artist_id,
            String artist_name) {
        this.album_id = album_id;
        this.album_name = album_name;
        this.album_img = album_img;
        this.album_release_day = album_release_day;
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

    public byte[] getAlbum_img() {
        return album_img;
    }

    public void setAlbum_img(byte[] album_img) {
        this.album_img = album_img;
    }

    public Date getAlbum_release_day() {
        return album_release_day;
    }

    public void setAlbum_release_day(Date album_release_day) {
        this.album_release_day = album_release_day;
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

}
