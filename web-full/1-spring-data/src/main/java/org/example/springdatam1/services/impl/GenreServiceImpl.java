package org.example.springdatam1.services.impl;

import org.example.springdatam1.entity.Genre;
import org.example.springdatam1.repository.GenreRepository;
import org.example.springdatam1.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {


    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Genre save(Genre entity) {
        return genreRepository.save(entity);
    }

    @Override
    public Genre findById(Long aLong) {
        return genreRepository.findById(aLong)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre update(Long aLong, Genre entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
