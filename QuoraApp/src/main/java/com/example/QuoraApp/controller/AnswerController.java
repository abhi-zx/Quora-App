package com.example.QuoraApp.controller;

import com.example.QuoraApp.models.AnswerModel;
import com.example.QuoraApp.models.User;
import com.example.QuoraApp.services.AnswerService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class AnswerController {

    private AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/question/{questionId}/answer")
    public AnswerModel addAnswer(@RequestBody AnswerModel answer, @PathVariable UUID questionId) {
        Long userId = answer.getUserId();
        return answerService.addAnswer(questionId, userId, answer.getText());

    }

    @PutMapping("/answer/{answerId}")
    public Optional<AnswerModel> updateAnswer(@PathVariable UUID answerId, @RequestBody String text){
      return answerService.updateAnswer(answerId,text);
    }

}
