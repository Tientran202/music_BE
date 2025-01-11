package com.example.be.dto.response.artist;

public class IndexStoryByStoryIdResponse {
    private String title;
    private int music_id;
    private String music_name;
    private int artist_id;
    private String artist_name;
    private byte[] img;
    private byte[] audio;

    public IndexStoryByStoryIdResponse() {
    }



    public IndexStoryByStoryIdResponse(String title, int music_id, String music_name, int artist_id, String artist_name,
            byte[] img, byte[] audio) {
        this.title = title;
        this.music_id = music_id;
        this.music_name = music_name;
        this.artist_id = artist_id;
        this.artist_name = artist_name;
        this.img = img;
        this.audio = audio;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public byte[] getAudio() {
        return audio;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }

}
