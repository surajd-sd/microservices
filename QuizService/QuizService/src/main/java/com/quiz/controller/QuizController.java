package com.quiz.controller;

import com.quiz.entity.Quiz;
import com.quiz.service.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {


    private QuizService quizService;
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    //create
    @PostMapping
    public Quiz create(@RequestBody Quiz quiz){
        return  quizService.add(quiz);
    }

    //getAll
    @GetMapping
    public List<Quiz> get(){
        return quizService.getAll();
    }

    //getOne
    @GetMapping("{id}")
    public Quiz getOne(@PathVariable Long id){
        return quizService.get(id);
    }
}
