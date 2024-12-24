package com.example.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.be.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByEmail(String email);

    Optional<Account> findByEmail(String email);

    Optional<Account> findByUsername(String username);

    default Optional<Account> findAccountByInfo(String info) {
        Optional<Account> optionalAccount = findByEmail(info);
        if (!optionalAccount.isPresent()) {
            optionalAccount = findByUsername(info);
        }
        return optionalAccount;
    }

    @Query(value = "SELECT a.id " +
            "FROM account a " +
            "WHERE a.username = :username", nativeQuery = true)
    int getIdAccountByUserName(@Param("username") String username);

    @Query(value = "SELECT l.music_id , m.music_name , m.img " +
            " FROM listening_history a " +
            " WHERE a.username = :accountId ", nativeQuery = true)
    int getlisteningHistory(@Param("accountId") String accountId);
}
