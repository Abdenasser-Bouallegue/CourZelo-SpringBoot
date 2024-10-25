package tn.esprit.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.esprit.entities.Option;
import tn.esprit.entities.Question;
import tn.esprit.entities.Quiz;
import tn.esprit.repositories.QuestionRepository;
import tn.esprit.repositories.quizRepository;


import java.util.List;

@Service
public class quizServiceImpl implements quizService{

    @Autowired
    private quizRepository QuizRepository;
    @Autowired
private QuestionRepository questionRepository;
    @Override
    public Quiz getQuizById(Long quizId) {
        return QuizRepository.findById(quizId).orElse(null);
    }

    @Override   @Transactional
    public Quiz saveQuiz(Quiz quiz) {
        {

            for (Question question : quiz.getQuestions()) {

                question.setQuiz(quiz);


                for (Option option : question.getOptions()) {

                    option.setQuestion(question);
                }
            }


            return QuizRepository.save(quiz);
        }


    }




    @Override
    public void updateQuiz(Quiz quiz) {
        QuizRepository.save(quiz);
    }

    @Override
    public void deleteQuiz(Long quizId) {
        QuizRepository.deleteById(quizId);
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return QuizRepository.findAll();
    }



}
