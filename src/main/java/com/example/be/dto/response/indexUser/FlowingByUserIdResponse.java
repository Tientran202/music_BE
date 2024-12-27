package com.example.be.dto.response.indexUser;

public class FlowingByUserIdResponse {
    private int artist_id;
    private String artist_name;
    private byte[] artist_img;

    public FlowingByUserIdResponse() {
    }

    public FlowingByUserIdResponse(int artist_id, String artist_name, byte[] artist_img) {
        this.artist_id = artist_id;
        this.artist_name = artist_name;
        this.artist_img = artist_img;
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

    public byte[] getArtist_img() {
        return artist_img;
    }

    public void setArtist_img(byte[] artist_img) {
        this.artist_img = artist_img;
    }

}
