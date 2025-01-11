package com.example.be.dto.response.artist;

public class StoryByArtistIdResponse {
    private int story_id;
    private String title;
    private int music_id;
    private byte[] img;

    public StoryByArtistIdResponse() {
    }

    public StoryByArtistIdResponse(int story_id, String title, int music_id, byte[] img) {
        this.story_id = story_id;
        this.title = title;
        this.music_id = music_id;
        this.img = img;
    }

    public int getStory_id() {
        return story_id;
    }

    public void setStory_id(int story_id) {
        this.story_id = story_id;
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

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

}
