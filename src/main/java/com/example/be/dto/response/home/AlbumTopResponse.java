package com.example.be.dto.response.home;

public class AlbumTopResponse {
    private int id;
    private String album_name;
    private byte[] img;

    public AlbumTopResponse() {
    }

    public AlbumTopResponse(int id, String album_name, byte[] img) {
        this.id = id;
        this.album_name = album_name;
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
}
