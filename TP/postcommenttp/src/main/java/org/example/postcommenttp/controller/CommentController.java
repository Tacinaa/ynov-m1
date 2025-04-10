package org.example.postcommenttp.controller;

import jakarta.validation.Valid;
import org.example.postcommenttp.dto.CommentDTO;
import org.example.postcommenttp.entity.CommentEntity;
import org.example.postcommenttp.entity.PostEntity;
import org.example.postcommenttp.service.CommentService;
import org.example.postcommenttp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllCommentsForPost(@PathVariable Long postId) {
        List<CommentDTO> comments = commentService.findByPostId(postId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(
            @PathVariable Long postId,
            @PathVariable Long commentId) {
        CommentEntity comment = commentService.findById(commentId);
        if (!comment.getPost().getId().equals(postId)) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(convertToDto(comment));
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentDTO commentDTO) {
        PostEntity post = postService.findById(postId);
        CommentEntity comment = convertToEntity(commentDTO);
        comment.setPost(post);
        CommentEntity saved = commentService.save(comment);

        System.out.printf("Commentaire créé le %s%n", LocalDateTime.now());

        return ResponseEntity.ok(convertToDto(saved));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentDTO commentDTO) {
        CommentEntity existing = commentService.findById(commentId);
        if (!existing.getPost().getId().equals(postId)) {
            return ResponseEntity.badRequest().body(null);
        }
        existing.setName(commentDTO.getName());
        existing.setEmail(commentDTO.getEmail());
        existing.setBody(commentDTO.getBody());

        CommentEntity updated = commentService.save(existing);
        return ResponseEntity.ok(convertToDto(updated));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId) {
        CommentEntity comment = commentService.findById(commentId);
        if (!comment.getPost().getId().equals(postId)) {
            return ResponseEntity.badRequest().body("Le commentaire ne correspond pas au post");
        }
        commentService.delete(commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }


    private CommentDTO convertToDto(CommentEntity comment) {
        return new CommentDTO(comment.getId(), comment.getName(), comment.getEmail(), comment.getBody());
    }

    private CommentEntity convertToEntity(CommentDTO dto) {
        return new CommentEntity(dto.getId(), dto.getName(), dto.getEmail(), dto.getBody(), null);
    }
}