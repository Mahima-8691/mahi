package com.blogapi.controller;

import com.blogapi.payload.CommentDto;
import com.blogapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;
    public CommentController(CommentService commentService) {

        this.commentService = commentService;
    }

    //http://localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value ="postId") long postId, @RequestBody CommentDto commentDto){
            CommentDto dto=commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> findCommentByPostId(@PathVariable(value ="postId") long postId){
        List<CommentDto> dtos = commentService.findCommentByPostId(postId);
        return dtos;
    }

    //http://localhost:8080/api/posts/1/comments/1
    @DeleteMapping("/posts/{postId}/comments/{id}")                                                                                                                                                                         
    public ResponseEntity<String>deleteCommentByPostId(@PathVariable(value ="postId") long postId, @PathVariable(value ="id") long id){
         commentService.deleteCommentByPostId(postId,id);
        return new ResponseEntity<>("comment is deleted", HttpStatus.OK);// this msg will show on postaman response.postman is use will for as frontend.
    }

    //http://localhost:8080/api/posts/1/comments/1
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentByPostId(@PathVariable(value ="postId") long postId, @PathVariable(value ="id") long id){
        CommentDto dto=commentService.getCommentByPostId(postId,id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/1/comments/1
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "id") Long commentId,
                                                    @RequestBody CommentDto commentDto){
        CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }
}


//        "name":"mike",
//        "email":"mikw@gmail.com",
//        "body":"nice Comment"
//