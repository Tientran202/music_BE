package com.example.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.be.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query(value = "select a.id , a.album_name , a.img from album a " +
            " order by listening_duration desc " +
            " limit 8 ", nativeQuery = true)
    List<Object[]> getTopAlbum();
}
