package org.example.postcommenttp.service;

import org.example.postcommenttp.entity.CommentEntity;

import java.util.List;

public interface CommentService {
    List<CommentEntity> findByPostId(Long postId);
    CommentEntity findById(Long id);
    CommentEntity save(CommentEntity comment);
    CommentEntity update(Long id, CommentEntity comment);
    void delete(Long id);
}