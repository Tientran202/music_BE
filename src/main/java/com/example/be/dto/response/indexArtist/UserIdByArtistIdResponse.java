package com.example.be.dto.response.indexArtist;

public class UserIdByArtistIdResponse {
    private int user_id;

    public UserIdByArtistIdResponse() {
    }

    public UserIdByArtistIdResponse(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
}
