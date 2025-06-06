package com.example.blogspringdto.service.impl;


import com.example.blogspringdto.dto.CommentDto;
import com.example.blogspringdto.entity.Comment;
import com.example.blogspringdto.entity.Post;
import com.example.blogspringdto.exception.BlogApiException;
import com.example.blogspringdto.exception.ResourceNotFoundException;
import com.example.blogspringdto.repository.CommentRepository;
import com.example.blogspringdto.repository.PostRepository;
import com.example.blogspringdto.service.CommentService;
import com.example.blogspringdto.utils.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = mapper.mapToEntity(commentDto);

        Post post = retrievePostEntityById(postId);

        comment.setPost(post);

        Comment newComment =  commentRepository.save(comment);

        return mapper.mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {

        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map(comment -> mapper.mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {

        Comment comment = commentById(postId, commentId);

        return mapper.mapToDto(comment);

    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentRequest) {

        Post post = retrievePostEntityById(postId);

        Comment comment = retrieveCommentById(commentId);

        badRequestException(comment, post);

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepository.save(comment);

        return mapper.mapToDto(updatedComment);

    }
    
    @Override
    public void deleteComment(long postId, long commentId) {


        Comment comment = commentById(postId, commentId);

        commentRepository.delete(comment);
    }


    private Comment commentById(long postId, long commentId) {


        Post post = retrievePostEntityById(postId);


        Comment comment = retrieveCommentById(commentId);


        badRequestException(comment, post);

        return comment;
    }



    private void badRequestException(Comment comment, Post post) {

        if (((comment.getPost().getId()) != (post.getId()))) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

    }


    private Post retrievePostEntityById(long postId) {

        return postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
    }


    private Comment retrieveCommentById(long commentId) {

        return commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "commentId", commentId));
    }

}
