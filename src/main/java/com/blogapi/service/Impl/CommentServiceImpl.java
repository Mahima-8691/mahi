package com.blogapi.service.Impl;

import com.blogapi.entity.Comment;
import com.blogapi.entity.Post;
import com.blogapi.exceptions.ResourceNotFoundException;
import com.blogapi.payload.CommentDto;
import com.blogapi.repository.CommentRepository;
import com.blogapi.repository.PostRepository;
import com.blogapi.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepo;

    private CommentRepository commentRepo;

    public CommentServiceImpl(PostRepository postRepo, CommentRepository commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        // retrieve post entity by id
        Post post=postRepo.findById(postId).orElseThrow( // this postId number.
                ()->new ResourceNotFoundException(postId)
        );

        Comment comment = new Comment(); // this comment for
        comment.setName(commentDto.getName()); // then it will copy the comment
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        comment.setPost(post); // for this post and  this post belong to     // set the comment for this post.

        Comment saveComment = commentRepo.save(comment); // save comment

        CommentDto dto = new CommentDto();
        dto.setId(saveComment.getId());
        dto.setName(saveComment.getName());
        dto.setEmail(saveComment.getEmail());
        dto.setBody(saveComment.getBody());



        return dto;
    }

    public List<CommentDto> findCommentByPostId(long postId){
        // retrieve post entity by id
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );


        List<Comment> comments = commentRepo.findByPostId(postId);

        return  comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());


    }

    @Override
    public void deleteCommentByPostId(long postId, long id) {
        // retrieve post entity by id
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );
        // retrieve comment by id
        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );

        commentRepo.deleteById(id);

    }

    @Override
    public CommentDto getCommentByPostId(long postId, long id) {

        // retrieve post entity by id
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );
        // retrieve comment by id
        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );

        CommentDto commentdto = mapToDto(comment);

        return commentdto;
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        // retrieve post entity by id
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId));

        // retrieve comment by id
        Comment comment = commentRepo.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException(commentId));

        comment.setName(commentDto.getName()); // we update the comment
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment = commentRepo.save(comment); // then save the comment
        return mapToDto(updatedComment);
    }





    CommentDto mapToDto(Comment comment){
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());
        return dto;

    }
}


















