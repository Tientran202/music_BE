package com.example.be.dto.response.indexArtist;

public class IndexArtistResponse {
    private String artist_name;
    private byte[] artist_image;
    private Long album_count;
    private Long playlist_count;
    private Long music_count;
    private Long flow_count;

    public IndexArtistResponse() {
    }

    public IndexArtistResponse(String artist_name, byte[] artist_image, Long album_count, Long playlist_count,
            Long music_count, Long flow_count) {
        this.artist_name = artist_name;
        this.artist_image = artist_image;
        this.album_count = album_count;
        this.playlist_count = playlist_count;
        this.music_count = music_count;
        this.flow_count = flow_count;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public byte[] getArtist_image() {
        return artist_image;
    }

    public Long getMusic_count() {
        return music_count;
    }

    public void setMusic_count(Long music_count) {
        this.music_count = music_count;
    }

    public void setArtist_image(byte[] artist_image) {
        this.artist_image = artist_image;
    }

    public Long getAlbum_count() {
        return album_count;
    }

    public void setAlbum_count(Long album_count) {
        this.album_count = album_count;
    }

    public Long getPlaylist_count() {
        return playlist_count;
    }

    public void setPlaylist_count(Long playlist_count) {
        this.playlist_count = playlist_count;
    }

    public Long getFlow_count() {
        return flow_count;
    }

    public void setFlow_count(Long flow_count) {
        this.flow_count = flow_count;
    }

}
