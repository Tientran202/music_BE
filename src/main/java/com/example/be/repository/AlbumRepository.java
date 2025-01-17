package com.example.be.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.be.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

        @Query(value = "select " +
                        " al.id , al.album_name , al.img , al.release_day , a.id , a.stage_name " +
                        " from album al " +
                        " join user a on al.user_id = a.id " +
                        " where al.hidden = 0 " +
                        " order by al.listening_duration desc ", nativeQuery = true)
        List<Object[]> getTopAlbum();

        @Query(value = "select al.id , al.album_name , al.release_day , al.img , a.id , a.stage_name " +
                        " from album al " +
                        " join user a on a.id = al.user_id " +
                        " where al.hidden = 0 ", nativeQuery = true)
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
                        " where u.id = :artistId " +
                        " limit 7 ", nativeQuery = true)
        List<Object[]> getAlbumByArtistIdlimit(@Param("artistId") int artistId);

        @Query(value = "select " +
                        " al.id , al.album_name , al.img " +
                        " from album al " +
                        " join user u on u.id = al.user_id " +
                        " where u.id = :artistId ", nativeQuery = true)
        List<Object[]> getAlbumByArtistId(@Param("artistId") int artistId);
        Optional<Album> findById(int albumId);


        @Query(value = "SELECT COUNT(*) FROM album", nativeQuery = true)
        Long getNumberOfAlbum();

        

}
