package com.example.be.dto.response.search;

public class SearchPlaylistResponse {
    private int playlist_id;
    private String playlist_name;
    private byte[] img;
    private String user_name;
    private int user_id;

    public SearchPlaylistResponse() {
    }

    public SearchPlaylistResponse(int playlist_id, String playlist_name, byte[] img, String user_name, int user_id) {
        this.playlist_id = playlist_id;
        this.playlist_name = playlist_name;
        this.img = img;
        this.user_name = user_name;
        this.user_id = user_id;
    }

    public int getPlaylist_id() {
        return playlist_id;
    }

    public void setPlaylist_id(int playlist_id) {
        this.playlist_id = playlist_id;
    }

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}
