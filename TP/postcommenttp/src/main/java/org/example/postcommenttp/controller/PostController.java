package org.example.postcommenttp.controller;

import jakarta.validation.Valid;
import org.example.postcommenttp.dto.PostDTO;
import org.example.postcommenttp.entity.PostEntity;
import org.example.postcommenttp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        PostEntity post = postService.findById(id);
        return ResponseEntity.ok(convertToDto(post));
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
        PostEntity post = convertToEntity(postDTO);
        PostEntity saved = postService.save(post);

        // Afficher la date de création en console
        System.out.printf("Post créé le %s%n", LocalDateTime.now());

        return ResponseEntity.ok(convertToDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @Valid @RequestBody PostDTO postDTO) {
        PostEntity updated = postService.update(id, convertToEntity(postDTO));
        return ResponseEntity.ok(convertToDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.ok("Post deleted successfully");
    }


    private PostDTO convertToDto(PostEntity post) {
        return new PostDTO(post.getId(), post.getTitle(), post.getContent());
    }

    private PostEntity convertToEntity(PostDTO dto) {
        return new PostEntity(dto.getId(), dto.getTitle(), dto.getContent(), null);
    }
}