package org.example.springdatam1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id",nullable = true)
    private Author author;

    @ManyToMany
    @JoinTable(name="book_genre",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="genre_id"))
    private Set<Genre> genres = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Book(Long id, String title, Author author, Publisher publisher, Set<Genre> genres) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genres = genres;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public @NotBlank(message = "Le livre doit avoir un titre") @Size(max = 200, message = "Le titre doit être inféreur à 200") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Le livre doit avoir un titre") @Size(max = 200, message = "Le titre doit être inféreur à 200") String title) {
        this.title = title;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", genres=" + genres +
                '}';
    }
}
