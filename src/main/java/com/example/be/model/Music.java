package com.example.be.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "music")
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String musicName;
    private Date Release_date;

    @ManyToOne
    @JoinColumn(name = "artistId", referencedColumnName = "id")
    private User artist;

    @Lob
    private byte[] audio;
    @Lob
    @Column(name = "img", columnDefinition = "LONGBLOB")
    private byte[] img;

    @ManyToOne
    @JoinColumn(name = "genreId", referencedColumnName = "id")
    private Genre genre;

    private BigDecimal duration;
    private float listening_duration;

    @Lob
    private String lyrics;

    private BigInteger plays;

    @ManyToOne
    @JoinColumn(name = "albumId", referencedColumnName = "id")
    private Album album;

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public User getArtist() {
        return artist;
    }

    public void setArtist(User artist) {
        this.artist = artist;
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

    public Genre getGenre() {
        return genre;
    }

    public Date getRelease_date() {
        return Release_date;
    }

    public void setRelease_date(Date release_date) {
        Release_date = release_date;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
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

    public BigInteger getPlays() {
        return plays;
    }

    public void setPlays(BigInteger plays) {
        this.plays = plays;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getListening_duration() {
        return listening_duration;
    }

    public void setListening_duration(float listening_duration) {
        this.listening_duration = listening_duration;
    }

}
