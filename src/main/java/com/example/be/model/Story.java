package com.example.be.model;

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
@Table(name = "story")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @Lob
    private byte[] audioStory;

    private String title;
    private float listening_duration;

    @ManyToOne
    @JoinColumn(name = "musicId", referencedColumnName = "id")
    private Music music;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getAudioStory() {
        return audioStory;
    }

    @Lob
    @Column(name = "img", columnDefinition = "LONGBLOB")
    private byte[] img;

    public void setAudioStory(byte[] audioStory) {
        this.audioStory = audioStory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public float getListening_duration() {
        return listening_duration;
    }

    public void setListening_duration(float listening_duration) {
        this.listening_duration = listening_duration;
    }

}
