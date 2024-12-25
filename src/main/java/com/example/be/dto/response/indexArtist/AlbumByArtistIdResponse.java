package com.example.be.dto.response.indexArtist;

public class AlbumByArtistIdResponse {
    private  int album_id;
    private String album_name;
    private byte[] album_img;
   
    public AlbumByArtistIdResponse() {
    }

    public AlbumByArtistIdResponse(int album_id, String album_name, byte[] album_img) {
        this.album_id = album_id;
        this.album_name = album_name;
        this.album_img = album_img;
    }

    public int getAlbum_id() {
        return album_id;
    }
    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }
    public byte[] getAlbum_img() {
        return album_img;
    }
    public void setAlbum_img(byte[] album_img) {
        this.album_img = album_img;
    }
    public String getAlbum_name() {
        return album_name;
    }
    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }


}