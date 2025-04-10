package org.example.postcommenttp.service;

import org.example.postcommenttp.entity.CommentEntity;
import org.example.postcommenttp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<CommentEntity> findByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public CommentEntity findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id " + id));
    }

    @Override
    public CommentEntity save(CommentEntity comment) {
        return commentRepository.save(comment);
    }

    @Override
    public CommentEntity update(Long id, CommentEntity comment) {
        CommentEntity existing = findById(id);
        existing.setName(comment.getName());
        existing.setEmail(comment.getEmail());
        existing.setBody(comment.getBody());
        return commentRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}