package tn.esprit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.entities.Quiz;
import tn.esprit.repositories.quizRepository;


import java.util.List;

@Service
public class QuizServiceImpl implements QuizService{

    @Autowired
    public quizRepository QuizRepository;

    @Override
    public List<Quiz> getAllQuizzes() {
        return QuizRepository.findAll();
    }

    @Override
    public Quiz getQuizById(Long quizId) {
        return QuizRepository.findById(quizId).get();
    }

    @Override
    public Quiz saveQuiz(Quiz quiz) {
        {
            return QuizRepository.save(quiz);
        }
    }
    @Override
    public Quiz updateQuiz(Quiz quiz) {
       return QuizRepository.save(quiz);
    }

    @Override
    public void deleteQuiz(Long quizId) {
        QuizRepository.deleteById(quizId);
    }




}
