package com.example.blogspringdto.service.impl;


import com.example.blogspringdto.dto.PostDto;
import com.example.blogspringdto.entity.Post;
import com.example.blogspringdto.exception.ResourceNotFoundException;
import com.example.blogspringdto.repository.PostRepository;
import com.example.blogspringdto.service.PostService;
import com.example.blogspringdto.utils.Mapper;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = mapper.mapToEntity(postDto);

        Post newPost = postRepository.save(post);

        PostDto postResponse = mapper.mapToDto(newPost);

        return postResponse;
    }

   @Override
    public List<PostDto> getAllPosts() {

        List<Post>  posts = (List<Post>) postRepository.findAll();

        List<PostDto> postDtoList =  posts.stream().map(post->mapper.mapToDto(post)).collect(Collectors.toList());


        return postDtoList;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = getPostByIdFromDatabase(id);
        return mapper.mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {

        Post post = getPostByIdFromDatabase(id);

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatePost = postRepository.save(post);
        return mapper.mapToDto(updatePost);
    }

    @Override
    public void deletePostById(long id) {

        Post post = getPostByIdFromDatabase(id);


        postRepository.deleteById(id);


    }


    private Post getPostByIdFromDatabase(long id) {

        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }



}
