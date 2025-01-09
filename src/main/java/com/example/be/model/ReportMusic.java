package com.example.be.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "report_music")
public class ReportMusic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "musicId", referencedColumnName = "id")
    private Music music;

    @ManyToOne
    @JoinColumn(name = "announcerId", referencedColumnName = "id")
    private User announcer;
    private Date day;
    private boolean hidden;
    private Date hidden_time;

    public Date getDay() {
        return day;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public Date getHidden_time() {
        return hidden_time;
    }

    public void setHidden_time(Date hidden_time) {
        this.hidden_time = hidden_time;
    }

    public void setDay(Date day) {
        this.day = day;
    }
    public void setDay() {
        this.day = new Date();
    }

    @Lob
    private String reportContent;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public User getAnnouncer() {
        return announcer;
    }

    public void setAnnouncer(User announcer) {
        this.announcer = announcer;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}