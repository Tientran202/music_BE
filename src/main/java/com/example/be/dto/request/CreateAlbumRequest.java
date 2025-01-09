package com.example.be.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class CreateAlbumRequest {
    private int id;
    private String album_name;
    private MultipartFile image;

    public CreateAlbumRequest() {
    }

    public CreateAlbumRequest(int id, String album_name, MultipartFile image) {
        this.id = id;
        this.album_name = album_name;
        this.image = image;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

}
