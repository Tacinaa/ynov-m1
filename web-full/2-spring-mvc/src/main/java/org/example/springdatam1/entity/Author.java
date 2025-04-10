package org.example.springdatam1.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="author_id")
    private Long id;

    @Size(min = 4, max = 50, message = "Le nom doit être compris entre 4 et 50")
    @NotBlank(message = "L'auteur doit avoir un nom")
    @Column(name = "author_name", unique = true, nullable = false, length = 50)
    private String name;


    @NotBlank(message = "L'auteur doit avoir un email")
    @Size(min = 4, max = 50, message = "L'email doit être compris entre 4 et 50")
    @Email(message = "Un email doit être valide")
    @Pattern(regexp = ".*\\.com", message = "L'email doit se terminer par .com")
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "author",  fetch = FetchType.EAGER,  cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Book> books = new HashSet<>();

    public Author(Long id, String name, String email, Set<Book> books) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.books = books;
    }

    public Author() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }

    public  String getEmail() {
        return email;
    }

    public void setEmail( String email) {
        this.email = email;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
               //  ", books=" + books +
                '}';
    }
}
