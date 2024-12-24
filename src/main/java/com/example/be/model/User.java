package com.example.be.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String stageName;
    private Date birthday;
    private String role;
    private String genre;
    private int numberViolation;
    private boolean request_artist;
    private Date time_request_artist;
    private Date time_debut;

    @Lob
    private byte[] avatar;
    @OneToOne
    @JoinColumn(name = "account", referencedColumnName = "id")
    private Account account;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public Date getTime_debut() {
        return time_debut;
    }

    public void setTime_debut(Date time_debut) {
        this.time_debut = time_debut;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getNumberViolation() {
        return numberViolation;
    }

    public void setNumberViolation(int numberViolation) {
        this.numberViolation = numberViolation;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRequest_artist() {
        return request_artist;
    }

    public void setRequest_artist(boolean request_artist) {
        this.request_artist = request_artist;
    }

    public Date getTime_request_artist() {
        return time_request_artist;
    }

    public void setTime_request_artist(Date time_request_artist) {
        this.time_request_artist = time_request_artist;
    }

}
