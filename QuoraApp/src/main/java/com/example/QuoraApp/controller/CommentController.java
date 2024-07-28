package com.example.QuoraApp.controller;


import com.example.QuoraApp.models.CommentModel;
import com.example.QuoraApp.services.CommentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CommentController {

    public CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }

    @PostMapping("/answer/{answerId}/comment")
    public CommentModel postComment(@RequestBody CommentModel comment, @PathVariable UUID answerId){
       return commentService.postComment(comment,answerId);

    }

    @PostMapping("/comment/{commentId}/comment")
    public CommentModel commentOnComment(@PathVariable UUID commentId,@RequestBody CommentModel comment){
        return commentService.commentOnComment(commentId,comment);
    }

}
