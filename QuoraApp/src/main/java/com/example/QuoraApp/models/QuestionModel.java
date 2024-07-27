package com.example.QuoraApp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class QuestionModel {

    @Id
    @Column(nullable=false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID questionId;

    @Column(nullable=false)
    private String title;

    @Column(nullable=false)
    private String body;

    @ElementCollection
    @CollectionTable(name = "topic_tags", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "tag")
    private List<String> topicTags;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
