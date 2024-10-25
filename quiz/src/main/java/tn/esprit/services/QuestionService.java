package tn.esprit.services;

import org.springframework.stereotype.Service;
import tn.esprit.entities.Question;

import java.util.List;


public interface QuestionService {
    Question getQuestionById(Long questionId);
    void saveQuestion(Question question);
    void updateQuestion(Question question);
    void deleteQuestion(Long questionId);


}
