package com.example.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.be.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long>{
    List<Genre> findAll();
}
