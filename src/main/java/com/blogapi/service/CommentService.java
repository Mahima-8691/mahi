package com.blogapi.service;

import com.blogapi.entity.Comment;
import com.blogapi.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);  //which post apply to the post Id after giving the post Id search whether is post is exist or not if the post exist.then we set the comment.

    public List<CommentDto> findCommentByPostId(long postId);

    void deleteCommentByPostId(long postId,long id);

    CommentDto getCommentByPostId(long postId, long id);

    CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);
}
