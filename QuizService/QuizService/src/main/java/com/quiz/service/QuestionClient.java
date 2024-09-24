package com.quiz.service;

import com.quiz.entity.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://localhost:8082",value = "Question-Client")

public interface QuestionClient {

    //get
    @GetMapping("/question/quiz/{quizId}") // we have already created this API
    List<Question> getQuestionOfQuiz(@PathVariable Long quizID);
}
// This is the interface which will be used in calling the API which is running at the 8082