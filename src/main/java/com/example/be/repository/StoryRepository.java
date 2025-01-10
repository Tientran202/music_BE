package com.example.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.be.model.Story;
@Repository
public interface StoryRepository extends JpaRepository<Story, Long>{
    
}
