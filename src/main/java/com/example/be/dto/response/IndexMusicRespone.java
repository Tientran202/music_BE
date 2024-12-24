package com.example.be.dto.response;

import java.math.BigDecimal;

public class IndexMusicRespone {
    private byte[] musicAudio;
    private byte[] musicImg;
    private BigDecimal duration;
    private String lyrics;
    private String music_name;
    private String artist_name;
    private String stage_name;

    public IndexMusicRespone() {

    }

    public IndexMusicRespone(byte[] musicAudio, byte[] musicImg, BigDecimal duration, String lyrics, String music_name,
            String artist_name, String stage_name) {
        this.musicAudio = musicAudio;
        this.musicImg = musicImg;
        this.duration = duration;
        this.lyrics = lyrics;
        this.music_name = music_name;
        this.artist_name = artist_name;
        this.stage_name = stage_name;
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
