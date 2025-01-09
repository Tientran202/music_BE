package com.example.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.be.model.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    

        List<Follow> findByUserId(int userId);

}
