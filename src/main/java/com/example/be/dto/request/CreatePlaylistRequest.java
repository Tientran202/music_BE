package com.example.be.dto.request;

public class CreatePlaylistRequest {
    private String playlist_name;
    private int user_id;
    private byte[] img;
    public CreatePlaylistRequest() {
    }
    public CreatePlaylistRequest(String playlist_name, int user_id, byte[] img) {
        this.playlist_name = playlist_name;
        this.user_id = user_id;
        this.img = img;
    }
    public String getPlaylist_name() {
        return playlist_name;
    }
    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public byte[] getImg() {
        return img;
    }
    public void setImg(byte[] img) {
        this.img = img;
    }
    

}
