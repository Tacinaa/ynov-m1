package com.example.blogspringdto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommentDto {

    private long id;

    @NotEmpty(message = "Name should not be null or empty")
    @Size(min = 2, message = "Name should have min. 2 characters")
    private String name;


    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    private Date date;

    @NotEmpty(message = "Comment body should not be null or empty")
    @Size(min = 10, message = "Comment body should have min. 10 characters")
    private String body;


}
