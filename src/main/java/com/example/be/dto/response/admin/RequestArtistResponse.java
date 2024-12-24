package com.example.be.dto.response.admin;

import java.util.Date;

public class RequestArtistResponse {
    private int id;
    private String name;
    private String stage_name;
    private Date time_request_artist;

    public RequestArtistResponse() {
    }

    public RequestArtistResponse(int id, String name, String stage_name, Date time_request_artist) {
        this.id = id;
        this.name = name;
        this.stage_name = stage_name;
        this.time_request_artist = time_request_artist;
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

    public String getStage_name() {
        return stage_name;
    }

    public void setStage_name(String stage_name) {
        this.stage_name = stage_name;
    }

    public Date getTime_request_artist() {
        return time_request_artist;
    }

    public void setTime_request_artist(Date time_request_artist) {
        this.time_request_artist = time_request_artist;
    }

}
