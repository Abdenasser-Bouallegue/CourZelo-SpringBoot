package tn.esprit.services;

import tn.esprit.entities.Quiz;

import java.util.List;

public interface quizService {
    Quiz getQuizById(Long quizId);
    Quiz saveQuiz(Quiz quiz);
    void updateQuiz(Quiz quiz);
    void deleteQuiz(Long quizId);
    List <Quiz> getAllQuizzes();
}
