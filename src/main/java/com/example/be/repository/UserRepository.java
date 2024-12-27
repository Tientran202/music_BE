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

        @Query(value = " select a.id , a.stage_name , a.avatar " +
                        " from user u " +
                        " join listening_history l on l.user_id = u.id " +
                        " join music m on l.music_id= m.id " +
                        " join user a on a.id = m.artist_id " +
                        " where u.account= :artistId ", nativeQuery = true)
        List<Object[]> getPopularArtist(@Param("artistId") int artistId);

        @Query(value = " select a.id , a.stage_name , a.avatar " +
                        " from user a " +
                        " where a.role= 'artist' ", nativeQuery = true)
        List<Object[]> getSearchArtist();

        @Query(value = " SELECT " +
                        " u.name AS artist_name, " +
                        " u.avatar AS artist_image, " +
                        " COUNT(DISTINCT a.id) AS album_count, " +
                        " COUNT(DISTINCT p.id) AS playlist_count, " +
                        " COUNT(DISTINCT f.id) AS flow_count " +
                        " FROM " +
                        " user u " +
                        " LEFT JOIN album a ON u.id = a.user_id " +
                        " LEFT JOIN play_list p ON u.id = p.user_id " +
                        " LEFT JOIN flow f ON u.id = f.artist_id " +
                        " WHERE " +
                        " u.id = :artistId " +
                        " GROUP BY " +
                        " u.id; ", nativeQuery = true)
        List<Object[]> getIndexArtist(@Param("artistId") int artistId);

        @Query(value = " select u.id , u.name , u.avatar , count(distinct f.id) " +
                        " from user u " +
                        " LEFT join flow f on f.user_id = u.id " +
                        " where u.id = :userId " +
                        " GROUP BY " +
                        "  u.id;", nativeQuery = true)
        List<Object[]> getIndexUser(@Param("userId") int userId);

        @Query(value = " select a.id , a.name , a.avatar " +
                        " from user u " +
                        " join flow f on f.user_id = u.id " +
                        " join user a on a.id = f.artist_id " +
                        " where u.id = :userId " +
                        " limit 7 ", nativeQuery = true)
        List<Object[]> getFllowingByUserId(@Param("userId") int userId);

        @Query("SELECT u.role FROM User u JOIN u.account a WHERE a.username = :username")
        List<String> getRoleByUsername(@Param("username") String username);

}
