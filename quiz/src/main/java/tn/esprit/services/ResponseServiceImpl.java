package tn.esprit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.entities.*;

import java.util.List;
import java.util.Optional;
@Service
public class ResponseServiceImpl implements ResponseService{
    private quizService QuizService;


    public int calculateScore(Long quizId, List<AnswerDTO> answers) throws QuizNotFoundException {
        Quiz quiz = QuizService.getQuizById(quizId);
        List<Question> questions = quiz.getQuestions();

        int score = 0;

        for (AnswerDTO answer : answers) {
            Optional<Question> questionOptional = findQuestionById(questions, answer.getQuestionId());
            if (questionOptional.isPresent()) {
                Question question = questionOptional.get();
                // Implement logic to check if the selected option is correct
                if (isAnswerCorrect(question, answer.getSelectedOptionId())) {
                    score++;
                }
            }
        }

        // Optionally, store the response in the database
        // responseRepository.save(new Response(quiz, student, score));

        return score;
    }

    private Optional<Question> findQuestionById(List<Question> questions, Long questionId) {
        return questions.stream()
                .filter(question -> question.getQuestionId().equals(questionId))
                .findFirst();
    }


    private boolean isAnswerCorrect(Question question, Long selectedOptionId) {
        List<Option> options = question.getOptions();

        // Find the selected option in the list
        Optional<Option> selectedOption = options.stream()
                .filter(option -> option.getOptionId().equals(selectedOptionId))
                .findFirst();

        // Check if the selected option is correct
        return selectedOption.isPresent() && selectedOption.get().isCorrectOption();
    }
}
