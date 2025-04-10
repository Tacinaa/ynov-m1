package org.example.postcommenttp.service;

import org.example.postcommenttp.entity.PostEntity;
import org.example.postcommenttp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<PostEntity> findAll() {
        return postRepository.findAll();
    }

    @Override
    public PostEntity findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + id));
    }

    @Override
    public PostEntity save(PostEntity post) {
        return postRepository.save(post);
    }

    @Override
    public PostEntity update(Long id, PostEntity post) {
        PostEntity existing = findById(id);
        existing.setTitle(post.getTitle());
        existing.setContent(post.getContent());
        return postRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}