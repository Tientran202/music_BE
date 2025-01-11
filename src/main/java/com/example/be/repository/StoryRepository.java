package com.example.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.be.model.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

    @Query(value = "SELECT s.id , " +
            " s.title , " +
            " s.music_id , " +
            " s.img " +
            " FROM story s " +
            " WHERE s.user_id = :artistId ", nativeQuery = true)
    List<Object[]> getAllStoryByArtistId(@Param("artistId") int artistId);


    // private String title;
    // private int music_id;
    // private String music_name;
    // private int artist_id;
    // private String artist_name;
    // private byte[] img;
    // private byte[] audio;


    @Query(value = "SELECT s.title, s.music_id, m.music_name , s.user_id , u.name , s.img , s.audio_story " +
            "FROM story s " +
            "JOIN user u ON s.user_id = u.id " +
            "JOIN music m ON s.music_id = m.id " +
            "WHERE s.id = :storyId", nativeQuery = true)
    List<Object[]> getIndexStoryByStoryId(@Param("storyId") int storyId);

}
