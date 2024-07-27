package com.example.QuoraApp.services;

import com.example.QuoraApp.Repositories.AnswerRepository;
import com.example.QuoraApp.Repositories.QuestionRepositorie;
import com.example.QuoraApp.Repositories.UserRepositories;
import com.example.QuoraApp.models.AnswerModel;
import com.example.QuoraApp.models.QuestionModel;
import com.example.QuoraApp.models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepositorie questionRepositorie;
    private final UserRepositories userRepositories;
    private final QuestionService questionService;

    public AnswerService(AnswerRepository answerRepository, QuestionRepositorie questionRepositorie, UserRepositories userRepositories, QuestionService questionService) {
        this.answerRepository = answerRepository;
        this.questionRepositorie = questionRepositorie;
        this.userRepositories = userRepositories;
        this.questionService = questionService;
    }

    public AnswerModel addAnswer(UUID questionId, Long userId, String text) {
        Optional<QuestionModel> questionOptional = questionRepositorie.findById(questionId);
        Optional<User> userOptional = userRepositories.findById(userId);

        if (questionOptional.isEmpty()) {
            throw new RuntimeException("Question not found");
        }

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        AnswerModel answer = new AnswerModel();
        answer.setText(text);
        answer.setQuestion(questionOptional.get());
        answer.setUser(userOptional.get());
        return answerRepository.save(answer);
    }

    public Optional<AnswerModel> updateAnswer(UUID answerId,String text){
        Optional <AnswerModel> answer = answerRepository.findById(answerId);
        answer.map(ans -> {
            ans.setText(text);
            return answerRepository.save(ans);
        });
        return answer;

    }
}
