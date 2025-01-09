package com.example.be.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "follow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "artistId", referencedColumnName = "id")
    private User artist;

    private boolean follow;

    public Follow() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getArtist() {
        return artist;
    }

    public void setArtist(User artist) {
        this.artist = artist;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }


    
}
