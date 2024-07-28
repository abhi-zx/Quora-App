package com.example.QuoraApp.services;


import com.example.QuoraApp.Repositories.AnswerRepository;
import com.example.QuoraApp.Repositories.CommentRepository;
import com.example.QuoraApp.Repositories.UserRepositories;
import com.example.QuoraApp.models.AnswerModel;
import com.example.QuoraApp.models.CommentModel;
import com.example.QuoraApp.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {

    public AnswerRepository answerRepository;
    public CommentRepository commentRepository;
    public UserRepositories userRepositories;

    public CommentService(AnswerRepository answerRepository, CommentRepository commentRepository, UserRepositories userRepositories) {
        this.answerRepository = answerRepository;
        this.commentRepository = commentRepository;
        this.userRepositories = userRepositories;
    }

    public CommentModel postComment(CommentModel comment, UUID answerId) {

        Optional<AnswerModel> answer = answerRepository.findById(answerId);
        User user = comment.getUser();
        Long userId = user.getId();

        CommentModel comment1 = new CommentModel();

        comment1.setAnswer(answer.get());
        comment1.setUser(userRepositories.findById(userId).get());
        comment1.setText(comment.getText());

        return commentRepository.save(comment1);

    }

    public CommentModel commentOnComment(UUID parentCommentId, CommentModel newComment) {

        Optional<CommentModel> parentComment = commentRepository.findById(parentCommentId);
        String newCommentText = newComment.getText();
        User user = newComment.getUser();
        Long userId = user.getId();

        CommentModel reply = new CommentModel();

        reply.setText(newCommentText);  //1. text comment added
        reply.setUser(userRepositories.findById(userId).get()); //2. user is added
        reply.setParentComment(parentComment.get()); //3. Parent is added
        reply.setAnswer(parentComment.get().getAnswer());
        return commentRepository.save(reply);
    }

}
