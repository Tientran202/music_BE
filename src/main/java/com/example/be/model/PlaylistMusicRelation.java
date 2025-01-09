package com.example.be.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PlaylistMusicRelation")
public class PlaylistMusicRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "playlistId", referencedColumnName = "id")
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "musicId", referencedColumnName = "id")
    private Music music;

    public PlaylistMusicRelation() {
    }

    public PlaylistMusicRelation(int id, Playlist playlist, Music music) {
        this.id = id;
        this.playlist = playlist;
        this.music = music;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
    

}
