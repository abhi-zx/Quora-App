package com.example.QuoraApp.controller;


import com.example.QuoraApp.models.QuestionModel;
import com.example.QuoraApp.models.User;
import com.example.QuoraApp.services.QuestionService;
import com.example.QuoraApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class QuestionController {

    private QuestionService questionService;
    private UserService userService;

    @Autowired
    public QuestionController(QuestionService questionService, UserService userService) {
        this.questionService = questionService;
        this.userService = userService;
    }

    @PostMapping("/question")
    public QuestionModel askQuestion(@RequestBody QuestionModel question) {
        User user = question.getUser(); // Get the User object from the request body
        Long userId = user.getId(); // Get the user ID from the User object

        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            User fetchedUser = userOptional.get();
            question.setUser(fetchedUser); // Set the fetched User object in the QuestionModel
            return questionService.askQuestion(question);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @GetMapping("/questions")
    public List<QuestionModel> searchQuestions(@RequestParam(value = "text", required = false) String text,
                                               @RequestParam(value = "tag", required = false) String tag) {
        return questionService.searchQuestions(text, tag);
    }

    @GetMapping("/question/{id}")
    public Optional<QuestionModel> findQuestionById(@PathVariable UUID id){
        return questionService.findQuestionById(id);
    }

}
