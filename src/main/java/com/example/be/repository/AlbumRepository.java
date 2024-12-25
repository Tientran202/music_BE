package com.example.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.be.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query(value = "select a.id , a.album_name , a.img from album a " +
            " order by listening_duration desc " +
            " limit 8 ", nativeQuery = true)
    List<Object[]> getTopAlbum();

    @Query(value = "select al.id , al.album_name , al.release_day , al.img , a.id , a.stage_name " +
            " from album al " +
            " join user a on a.id = al.user_id ", nativeQuery = true)
    List<Object[]> getSearchAlbum();

    @Query(value = "select al.id , al.album_name  , u.stage_name , al.release_day, al.img " +
            " from album al " +
            " join user u on u.id = al.user_id " +
            " where al.id = :albumId ", nativeQuery = true)
    List<Object[]> getIndexAlbum(@Param("albumId") int albumId);

    @Query(value = "select " +
            " al.id , al.album_name , al.img " +
            " from album al " +
            " join user u on u.id = al.user_id " +
            " where u.id = :artistId "+
            " limit 7 ", nativeQuery = true)
    List<Object[]> getAlbumByArtistId(@Param("artistId") int artistId);

}
