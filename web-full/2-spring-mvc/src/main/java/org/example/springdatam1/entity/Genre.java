package org.example.springdatam1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=3, message = "Le nom du genre doit être supérieur à 3")
    @NotBlank(message = "Le genre doit disposer d'un nom")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private Set<Book> books = new HashSet<>();

    public Genre(Long id, String name, Set<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }


    public Genre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Size(min = 3, message = "Le nom du genre doit être supérieur à 3") @NotBlank(message = "Le genre doit disposer d'un nom") String getName() {
        return name;
    }

    public void setName(@Size(min = 3, message = "Le nom du genre doit être supérieur à 3") @NotBlank(message = "Le genre doit disposer d'un nom") String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
