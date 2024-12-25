package com.example.be.dto.response.indexArtist;

public class PlaylistByArtistIdResponse {
    private  int playlist_id;
    private String playlist_name;
    private byte[] playlist_img;
    public PlaylistByArtistIdResponse() {
    }
    public PlaylistByArtistIdResponse(int playlist_id, String playlist_name, byte[] playlist_img) {
        this.playlist_id = playlist_id;
        this.playlist_name = playlist_name;
        this.playlist_img = playlist_img;
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
    public byte[] getPlaylist_img() {
        return playlist_img;
    }
    public void setPlaylist_img(byte[] playlist_img) {
        this.playlist_img = playlist_img;
    }

    
}
