package com.example.be.dto.response.admin;

public class AllMusicUnconfirmedResponse {
    private int id;
    private String name;
    private String music_name;

    public AllMusicUnconfirmedResponse() {
    }

    public AllMusicUnconfirmedResponse(int id, String name, String music_name) {
        this.id = id;
        this.name = name;
        this.music_name = music_name;
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

    public String getMusic_name() {
        return music_name;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

}
