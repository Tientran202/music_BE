package com.example.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.be.model.ListeningHistory;

public interface ListeningHistoryRepository extends JpaRepository<ListeningHistory, Long> {
    @Query(value = " select m.id , m.music_name , m.img " +
            " from listening_history l " +
            " join user u on u.id = l.user_id " +
            " join music m on m.id = l.music_id " +
            " where u.account = :account " +
            " limit 8", nativeQuery = true)
    List<Object[]> getListeningHistory(@Param("account") int account);
}
