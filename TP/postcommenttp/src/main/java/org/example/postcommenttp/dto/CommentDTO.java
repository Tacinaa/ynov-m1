package org.example.postcommenttp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CommentDTO {

    private Long id;

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(min = 4, message = "Le nom doit contenir au moins 4 caractères")
    private String name;

    @NotBlank(message = "L'email ne peut pas être vide")
    @Email(message = "Email invalide")
    @Pattern(regexp = ".*\\.net$", message = "L'email doit se terminer par .net")
    private String email;

    @NotBlank(message = "Le corps du commentaire ne peut pas être vide")
    private String body;

    public CommentDTO() {
    }

    public CommentDTO(Long id, String name, String email, String body) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}