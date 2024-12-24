package com.example.be.dto.response.search;

public class SearchArtistResponse {
    private int id;
    private String name;
    private byte[] img;

    public SearchArtistResponse() {
    }

    public SearchArtistResponse(int id, String name, byte[] img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

}
