package com.example.be.dto.response.artist;

public class MusicByArtistIdResponse {
    private int music_id;
    private String music_name;
    private byte[] music_img;
    private boolean confir;
    private boolean hidden;

    public MusicByArtistIdResponse() {
    }

    public MusicByArtistIdResponse(int music_id, String music_name, byte[] music_img) {
        this.music_id = music_id;
        this.music_name = music_name;
        this.music_img = music_img;
    }

    public MusicByArtistIdResponse(int music_id, String music_name, byte[] music_img, boolean confir, boolean hidden) {
        this.music_id = music_id;
        this.music_name = music_name;
        this.music_img = music_img;
        this.confir = confir;
        this.hidden = hidden;
    }

    public boolean isConfir() {
        return confir;
    }

    public void setConfir(boolean confir) {
        this.confir = confir;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public int getMusic_id() {
        return music_id;
    }

    public void setMusic_id(int music_id) {
        this.music_id = music_id;
    }

    public String getMusic_name() {
        return music_name;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public byte[] getMusic_img() {
        return music_img;
    }

    public void setMusic_img(byte[] music_img) {
        this.music_img = music_img;
    }

}
