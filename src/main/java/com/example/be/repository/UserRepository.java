package com.example.be.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.be.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findById(int id);

        @Query(value = "select u.id ,u.name , u.stage_name , u.time_request_artist " +
                        " from user u " +
                        " where u.request_artist = 1", nativeQuery = true)
        List<Object[]> findAllRequestToBecomeArtist();

        @Query(value = "SELECT u.id AS id, " +
                        " u.name AS user_name, " +
                        " COUNT(DISTINCT a.id) AS album_count, " +
                        " COUNT(DISTINCT m.id) AS music_count, " +
                        " u.time_debut " +
                        " FROM user u " +
                        " LEFT JOIN album a ON u.id = a.user_id " +
                        " LEFT JOIN music m ON u.id = m.artist_id " +
                        " WHERE u.role = 'artist' " +
                        " GROUP BY u.id, u.name, u.time_debut;", nativeQuery = true)
        List<Object[]> findAllArtist();

        @Query(value = "select u.id ,u.name , a.register_date " +
                        " from user u " +
                        " join account a on a.id = u.account" +
                        " where u.role = 'user' ", nativeQuery = true)
        List<Object[]> findAllUser();

        @Query(value = " select a.id , a.stage_name , a.avatar "+
        " from user u "+
        " join listening_history l on l.user_id = u.id "+
        " join music m on l.music_id= m.id "+
        " join user a on a.id = m.artist_id "+
        " where u.account= :artistId "+
        " limit 8 ", nativeQuery = true)
        List<Object[]> getPopularArtist(@Param("artistId") int artistId);


}
