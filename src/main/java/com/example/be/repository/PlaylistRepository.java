package com.example.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.be.model.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    @Query(value = " SELECT p.id, p.playlist_name, p.img , u.name , u.id " +
            " FROM play_list p " +
            " join user u on u.id = p.user_id ", nativeQuery = true)
    List<Object[]> getSearchPlayList();

    @Query(value = "select " +
            " pl.id , pl.playlist_name , pl.img " +
            " from play_list pl " +
            " join user u on u.id = pl.user_id " +
            " where u.id = :artistId " +
            " limit 7 ", nativeQuery = true)
    List<Object[]> getPlaylistByArtistId(@Param("artistId") int artistId);

    @Query(value = " select pl.id , pl.playlist_name , pl.img " +
            " from play_list pl " +
            " left join user u on u.id = pl.user_id " +
            " where u.id = :userId " +
            " limit 7 ", nativeQuery = true)
    List<Object[]> getPlaylistByUserId(@Param("userId") int userId);

    @Query(value = "select " +
                        " al.id , al.album_name , al.img , al.release_day , a.id , a.stage_name " +
                        " from album al "+
                        " join user a on al.user_id = a.id ", nativeQuery = true)
        List<Object[]> getAllPlayList();
}
