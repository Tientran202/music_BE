package com.example.be.dto.response.indexMusic;

import java.math.BigDecimal;

public class IndexMusicResponse {
    private byte[] musicAudio;
    private byte[] musicImg;
    private BigDecimal duration;
    private String lyrics;
    private String music_name;
    private String artist_name;
    private String stage_name;
    private int artist_id;

    public IndexMusicResponse() {

    }

    public IndexMusicResponse(byte[] musicAudio, byte[] musicImg, BigDecimal duration, String lyrics, String music_name,
            String artist_name, String stage_name, int artist_id) {
        this.musicAudio = musicAudio;
        this.musicImg = musicImg;
        this.duration = duration;
        this.lyrics = lyrics;
        this.music_name = music_name;
        this.artist_name = artist_name;
        this.stage_name = stage_name;
        this.artist_id = artist_id;
    }

    public byte[] getMusicAudio() {
        return musicAudio;
    }

    public void setMusicAudio(byte[] musicAudio) {
        this.musicAudio = musicAudio;
    }

    public byte[] getMusicImg() {
        return musicImg;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public void setMusicImg(byte[] musicImg) {
        this.musicImg = musicImg;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getMusic_name() {
        return music_name;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getStage_name() {
        return stage_name;
    }

    public void setStage_name(String stage_name) {
        this.stage_name = stage_name;
    }

}
