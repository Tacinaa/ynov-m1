package org.example.springdatam1.services.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.springdatam1.entity.Author;
import org.example.springdatam1.repository.AuthorRepository;
import org.example.springdatam1.repository.BookRepository;
import org.example.springdatam1.services.AuthorService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author save(Author entity) {
        return authorRepository.save(entity);
    }

    @Override
    @Transactional
    public Author findById(Long aLong) {
        Author author = authorRepository
                .findById(aLong)
                .orElseThrow(
                        () -> new EntityNotFoundException("Author not found"));

       // Hibernate.initialize(author.getBooks());
        return author;
    }

    @Override
   // @Transactional
    public List<Author> findAll() {

        return authorRepository.findAll();
    }

    @Override
    public Author update(Long aLong, Author entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {
        authorRepository.deleteById(aLong);
    }
}
