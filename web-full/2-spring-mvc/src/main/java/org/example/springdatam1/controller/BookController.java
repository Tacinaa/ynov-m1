package org.example.springdatam1.controller;

import jakarta.validation.Valid;
import org.example.springdatam1.dto.BookCreateDto;
import org.example.springdatam1.entity.Author;
import org.example.springdatam1.entity.Book;
import org.example.springdatam1.entity.Genre;
import org.example.springdatam1.entity.Publisher;
import org.example.springdatam1.services.AuthorService;
import org.example.springdatam1.services.BookService;
import org.example.springdatam1.services.GenreService;
import org.example.springdatam1.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;


    @Autowired
    GenreService genreService;

    @Autowired
    PublisherService publisherService;

    @GetMapping
    public List<Book> getAllBook() {
        return bookService.findAll();
    }


    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookCreateDto bookCreateDto) {

            Author author = authorService.findById(bookCreateDto.getAuthorId());

            Publisher publisher = publisherService.findById(bookCreateDto.getPublisherId());

            Set<Genre> genreSet = new HashSet<>();

            for (Long id : bookCreateDto.getGenreIds()) {
                Genre genre = genreService.findById(id);
                if (genre != null) {
                    genreSet.add(genre);
                }
            }
            Book book = new Book();
            book.setTitle(bookCreateDto.getTitle());
            book.setAuthor(author);
            book.setPublisher(publisher);
            book.setGenres(genreSet);

            return ResponseEntity.ok(bookService.save(book));
    }

}