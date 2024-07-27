package com.example.QuoraApp.services;
import com.example.QuoraApp.Repositories.QuestionRepositorie;
import com.example.QuoraApp.models.QuestionModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionService {

    public QuestionRepositorie questionRepositorie;


    public QuestionService(QuestionRepositorie questionRepositorie){
        this.questionRepositorie=questionRepositorie;
    }

    public QuestionModel askQuestion(QuestionModel question){
        return questionRepositorie.save(question);
    }
//    searchQuestions(text, tag)

    public List<QuestionModel> searchQuestions(String text, String tag) {
        if (text != null && tag != null) {
            return questionRepositorie.findByTitleContainingOrBodyContainingAndTopicTagsContaining(text, text, tag);
        } else if (text != null) {
            return questionRepositorie.findByTitleContainingOrBodyContaining(text, text);
        } else if (tag != null) {
            return questionRepositorie.findByTopicTagsContaining(tag);
        } else {
            return questionRepositorie.findAll();
        }
    }

    public Optional<QuestionModel> findQuestionById(UUID id){
     return questionRepositorie.findById(id);
    }
}
