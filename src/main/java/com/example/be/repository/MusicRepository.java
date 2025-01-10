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
                        "WHERE g.id = :genreId and m.confirmation = 1 and m.hidden = 0 ", nativeQuery = true)
        List<Object[]> findMusicByGenreId(@Param("genreId") int genreId);

        @Query(value = "SELECT m.audio, m.img, m.duration, m.lyrics, m.music_name, u.name, u.stage_name , m.artist_id "
                        +
                        "FROM music m " +
                        "JOIN user u ON m.artist_id = u.id " +
                        "WHERE m.id = :musicId", nativeQuery = true)
        List<Object[]> findIndexMusicByMusicId(@Param("musicId") int musicId);

        @Query(value = "SELECT m.audio, m.img, m.duration, m.lyrics, m.music_name, u.name, u.stage_name , m.artist_id "
                        +
                        "FROM music m " +
                        "JOIN user u ON m.artist_id = u.id " +
                        "WHERE m.id = :musicId ", nativeQuery = true)
        List<Object[]> findIndexMusicByMusicIdForAdmin(@Param("musicId") int musicId);

        @Query(value = " SELECT m.id, m.music_name, m.img " +
                        " FROM music m " +
                        " where  m.confirmation = 1 and m.hidden = 0 " +
                        " order by m.release_date desc ", nativeQuery = true)
        List<Object[]> getNewMusic();

        @Query(value = " SELECT m.id, m.music_name, m.img , u.stage_name , m.duration " +
                        " FROM music m " +
                        " join user u on u.id = m.artist_id " +
                        " where m.confirmation = 1 and m.hidden = 0 ", nativeQuery = true)
        List<Object[]> getSearchMusic();

        @Query(value = " select m.id , m.music_name , m.img , m.duration , m.audio " +
                        " from music m " +
                        " where m.album_id = :albumId and m.confirmation = 1 and m.hidden = 0 ", nativeQuery = true)
        List<Object[]> getMusicByAlbum(@Param("albumId") int albumId);

        @Query(value = " select m.id , m.music_name , m.img , m.duration , m.audio " +
                        " from music m " +
                        " join playList_music_Relation r on r.music_id = m.id " +
                        " where r.playlist_id = :playlistId and m.hidden = 0 ", nativeQuery = true)
        List<Object[]> getMusicByPlaylistId(@Param("playlistId") int playlistId);

        @Query(value = " select m.id , m.music_name , m.img " +
                        " from music m " +
                        " where m.artist_id = :artistId and m.confirmation = 1 and m.hidden = 0 ", nativeQuery = true)
        List<Object[]> getSuggestedMusicByArtistId(@Param("artistId") int artistId);

        @Query(value = " select m.id , m.music_name , m.img " +
                        " from music m " +
                        " where m.artist_id = :artistId ", nativeQuery = true)
        List<Object[]> getSuggestedMusicForAdminResponse(@Param("artistId") int artistId);

        List<Music> findAllByArtistId(int artistId);

        @Query(value = " select m.id , m.music_name " +
                        " from music m " +
                        " where m.artist_id = :artistId and m.album_id is null and m.confirmation = 1 and m.hidden = 0 and m.album_id is null", nativeQuery = true)
        List<Object[]> findAllForAlbumByArtistId(@Param("artistId") int artistId);

        @Query(value = " select m.id , m.music_name , m.img" +
                        " from music m " +
                        " where m.artist_id = :artistId and m.confirmation = 1 and m.hidden = 0 ", nativeQuery = true)
        List<Object[]> getMusicByArtistId(@Param("artistId") int artistId);

        @Query(value = " select m.id , m.music_name , m.img , m.confirmation , m.hidden " +
                        " from music m " +
                        " where m.artist_id = :artistId ", nativeQuery = true)
        List<Object[]> getAllMusicCByArtistId(@Param("artistId") int artistId);

        @Query(value = " select m.id , m.music_name , m.img" +
                        " from music m " +
                        " where m.artist_id = :artistId and m.confirmation = 1 and m.hidden = 0 and m.album_id is null", nativeQuery = true)
        List<Object[]> getMusicByArtistIdForCreateAlbum(@Param("artistId") int artistId);

        @Query(value = " select m.id , m.music_name , m.img" +
                        " from music m " +
                        " where m.artist_id = :artistId and m.confirmation = 1 and m.hidden = 0", nativeQuery = true)
        List<Object[]> getAllMusicByArtistId(@Param("artistId") int artistId);

        @Query(value = " select m.id , u.name , m.music_name" +
                        " from music m " +
                        " join user u on u.id = m.artist_id " +
                        " where m.confirmation = 0", nativeQuery = true)
        List<Object[]> getAllMusicUnconfirmed();

        @Query(value = "SELECT COUNT(*) FROM music ", nativeQuery = true)
        Long getNumberOfMusic();

        @Query(value = " SELECT  m.id , m.music_name, u.name,  ur.name , MAX(rm.day),  GROUP_CONCAT(rm.report_content ) ,  MAX(rm.hidden_time) ,  m.hidden "+
                        " FROM music m " +
                        " JOIN  report_music rm ON m.id = rm.music_id " +
                        " JOIN  user u ON u.id = m.artist_id " +
                        " JOIN  user ur ON ur.id = rm.announcer_id " +
                        " WHERE  m.hidden = true " +
                        " GROUP BY  m.id, m.music_name, u.name, ur.name, m.hidden ", nativeQuery = true)
        List<Object[]> findAllHiddenMuisc();
}
