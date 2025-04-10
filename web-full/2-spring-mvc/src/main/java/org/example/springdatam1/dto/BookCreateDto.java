package org.example.springdatam1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;


public class BookCreateDto {

    @NotBlank(message = "Le livre doit avoir un titre")
    @Size(min=5,max = 200, message = "Le titre doit être inféreur à 200 et supérieur à 5")
    private String title;
    private Long authorId;
    private Long publisherId;
    private Set<Long> genreIds;

    public BookCreateDto(String title, Long authorId, Long publisherId, Set<Long> genreIds) {
        this.title = title;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.genreIds = genreIds;
    }

    public BookCreateDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public Set<Long> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(Set<Long> genreIds) {
        this.genreIds = genreIds;
    }
}


