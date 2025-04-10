package org.example.postcommenttp.dto;

import jakarta.validation.constraints.NotBlank;

public class PostDTO {

    private Long id;

    @NotBlank(message = "Le titre ne peut pas être vide")
    private String title;

    @NotBlank(message = "Le contenu ne peut pas être vide")
    private String content;

    public PostDTO() {
    }

    public PostDTO(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}