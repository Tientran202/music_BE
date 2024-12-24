package com.example.be.dto.response.admin;

import java.util.Date;

public class ListArtistResponse {
    private int id;
    private String user_name;
    private Long album_count;
    private Long music_count;
    private Date time_debut;

    public ListArtistResponse() {
    }

    public ListArtistResponse(int id, String user_name, Long album_count, Long music_count, Date time_debut) {
        this.id = id;
        this.user_name = user_name;
        this.album_count = album_count;
        this.music_count = music_count;
        this.time_debut = time_debut;
    }

    public Long getAlbum_count() {
        return album_count;
    }

    public void setAlbum_count(Long album_count) {
        this.album_count = album_count;
    }

    public Long getMusic_count() {
        return music_count;
    }

    public void setMusic_count(Long music_count) {
        this.music_count = music_count;
    }

    public Date getTime_debut() {
        return time_debut;
    }

    public void setTime_debut(Date time_debut) {
        this.time_debut = time_debut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

}
