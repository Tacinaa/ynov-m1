package org.example.postcommenttp.service;

import org.example.postcommenttp.entity.PostEntity;

import java.util.List;

public interface PostService {
    List<PostEntity> findAll();
    PostEntity findById(Long id);
    PostEntity save(PostEntity post);
    PostEntity update(Long id, PostEntity post);
    void delete(Long id);
}