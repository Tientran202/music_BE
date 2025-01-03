package com.example.be.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.be.model.Music;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
        Optional<Music> findById(int id);

        @Query(value = "SELECT m.id AS musicId, " +
                        "g.genre AS genre, " +
                        "m.img AS musicImg, " +
                        "m.music_name AS musicName, " +
                        "u.name AS userName " +
                        "FROM music m " +
                        "JOIN genre g ON m.genre_id = g.id " +
                        "JOIN user u ON m.artist_id = u.id " +
                        "WHERE g.id = :genreId", nativeQuery = true)
        List<Object[]> findMusicByGenreId(@Param("genreId") int genreId);

        @Query(value = "SELECT m.audio, m.img, m.duration, m.lyrics, m.music_name, u.name, u.stage_name , m.artist_id " +
                        "FROM music m " +
                        "JOIN user u ON m.artist_id = u.id " +
                        "WHERE m.id = :musicId", nativeQuery = true)
        List<Object[]> findIndexMusicByMusicId(@Param("musicId") int musicId);

        @Query(value = " SELECT m.id, m.music_name, m.img " +
                        " FROM music m " +
                        " order by m.release_date desc ", nativeQuery = true)
        List<Object[]> getNewMusic();

        @Query(value = " SELECT m.id, m.music_name, m.img , u.stage_name , m.duration " +
                        " FROM music m " +
                        " join user u on u.id = m.artist_id ", nativeQuery = true)
        List<Object[]> getSearchMusic();

        @Query(value = " select m.id , m.music_name , m.img , m.duration , m.audio " +
                        " from music m " +
                        " where m.album_id = :albumId ", nativeQuery = true)
        List<Object[]> getMusicByAlbum(@Param("albumId") int albumId);

        @Query(value = " select m.id , m.music_name , m.img " +
                        " from music m " +
                        " where m.artist_id = :artistId ", nativeQuery = true)
        List<Object[]> getSuggestedMusicByArtistId(@Param("artistId") int artistId);

        
}
