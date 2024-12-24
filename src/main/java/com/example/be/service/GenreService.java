package com.example.be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.be.model.Genre;
import com.example.be.repository.GenreRepository;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;
    
    public List<Genre> getQuantityGenre(){
        return  genreRepository.findAll();
    }
}
