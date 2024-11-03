package tn.esprit.services;

import org.springframework.stereotype.Service;
import tn.esprit.entities.Quiz;

import java.util.List;

@Service
public interface QuizService {
    Quiz getQuizById(Long quizId);
    Quiz saveQuiz(Quiz quiz);
    Quiz updateQuiz(Quiz quiz);
    void deleteQuiz(Long quizId);
    List <Quiz> getAllQuizzes();
}
