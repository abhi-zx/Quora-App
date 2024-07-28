package com.example.QuoraApp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class CommentModel {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID comment_id;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private CommentModel parentComment;

    @ManyToOne
    @JoinColumn(name = "answer_id", nullable = false)
    private AnswerModel answer;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<CommentModel> replies;

}
