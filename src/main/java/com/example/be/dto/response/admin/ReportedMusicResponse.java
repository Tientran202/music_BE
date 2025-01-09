package com.example.be.dto.response.admin;

import java.util.Date;

public class ReportedMusicResponse {
    private int report_id;
    private int music_id;
    private String music_name;
    private String artist;
    private String reported_name;
    private Date day;
    private String report_content;

    public ReportedMusicResponse() {
    }

    public ReportedMusicResponse(int report_id, int music_id, String music_name, String artist, String reported_name,
            Date day, String report_content) {
        this.report_id = report_id;
        this.music_id = music_id;
        this.music_name = music_name;
        this.artist = artist;
        this.reported_name = reported_name;
        this.day = day;
        this.report_content = report_content;
    }

    public String getMusic_name() {
        return music_name;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public String getReported_name() {
        return reported_name;
    }

    public void setReported_name(String reported_name) {
        this.reported_name = reported_name;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getReport_content() {
        return report_content;
    }

    public void setReport_content(String report_content) {
        this.report_content = report_content;
    }

    public int getMusic_id() {
        return music_id;
    }

    public void setMusic_id(int music_id) {
        this.music_id = music_id;
    }

}
