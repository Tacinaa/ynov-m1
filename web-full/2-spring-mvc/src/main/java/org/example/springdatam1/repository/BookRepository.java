package org.example.springdatam1.repository;

import org.example.springdatam1.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    @Query("SELECT b FROM Book b JOIN b.genres g where g.name = :genreName")
    List<Book> findByGenreName(@Param("genreName") String genreName);

}
