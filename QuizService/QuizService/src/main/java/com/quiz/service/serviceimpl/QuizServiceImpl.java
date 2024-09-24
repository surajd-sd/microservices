package com.quiz.service.serviceimpl;

import com.quiz.entity.Quiz;
import com.quiz.repository.QuizRepository;
import com.quiz.service.QuestionClient;
import com.quiz.service.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service    // we use this annotation for autowire purpose also
public class QuizServiceImpl implements QuizService {


    private QuizRepository quizRepository;
    private QuestionClient questionClient;

    public QuizServiceImpl(QuizRepository quizRepository,QuestionClient questionClient) {
        this.quizRepository = quizRepository;
        this.questionClient=questionClient;
    }

    @Override
    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz);
    }

//    @Override
//    public List<Quiz> getAll() {
//        return quizRepository.findAll();
//    }

    @Override
    public List<Quiz> getAll() {
        List<Quiz> quizes=quizRepository.findAll();
       List<Quiz> newQuizList= quizes.stream().map(quiz -> {
            quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getQuizId()));
            return quiz;
        }).collect(Collectors.toList());
       return newQuizList;
    }

//    @Override
//    public Quiz get(Long id) {
//        return quizRepository.findById(id).orElseThrow(()->new RuntimeException("Quiz not found"));
//    }

    @Override
    public Quiz get(Long id) {
        Quiz quiz=quizRepository.findById(id).orElseThrow(()->new RuntimeException("Quiz not found"));
        quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getQuizId()));
        return quiz;
    }
}
