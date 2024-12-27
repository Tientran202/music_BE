package com.example.be.dto.response.home;

import java.util.Date;

public class AlbumTopResponse {
    private int id;
    private String album_name;
    private byte[] img;
    private Date release_day;
    private int artist_id;
    private String stage_name;

    public AlbumTopResponse() {
    }

    public AlbumTopResponse(int id, String album_name, byte[] img, Date release_day, int artist_id, String stage_name) {
        this.id = id;
        this.album_name = album_name;
        this.img = img;
        this.release_day = release_day;
        this.artist_id = artist_id;
        this.stage_name = stage_name;
    }

    public Date getRelease_day() {
        return release_day;
    }

    public void setRelease_day(Date release_day) {
        this.release_day = release_day;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public String getStage_name() {
        return stage_name;
    }

    public void setStage_name(String stage_name) {
        this.stage_name = stage_name;
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
}
