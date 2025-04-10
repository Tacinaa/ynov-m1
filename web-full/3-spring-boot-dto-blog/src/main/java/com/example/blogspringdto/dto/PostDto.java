package com.example.blogspringdto.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.util.Set;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    private long id;

    @NotEmpty
    @Size(min = 2, message = "Post title should have min. 2 characters")
    private String title;


    @NotEmpty
    @Size(min = 10, max=255, message = "Post description should have 10 characters")
    private String description;


    @NotEmpty
    private String content;

    private Set<CommentDto> comments;

}
