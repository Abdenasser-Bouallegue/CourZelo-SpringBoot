package tn.esprit.Controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.entities.*;
import tn.esprit.repositories.quizRepository;
import tn.esprit.services.quizService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/quizzes")
@AllArgsConstructor
@Slf4j
public class quizController {
private quizService QuizService;
    private quizRepository QuizRepository;
    @GetMapping("/show")
    public List<QuizDTO> getAllQuizzes() {
        List<Quiz> quizzes = QuizService.getAllQuizzes();
        return quizzes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private QuizDTO convertToDto(Quiz quiz) {
        QuizDTO quizDto = new QuizDTO();
        quizDto.setId(quiz.getId());
        quizDto.setTitle(quiz.getTitle());
        quizDto.setQuestions(quiz.getQuestions().stream()
                .map(this::convertQuestionToDto)
                .collect(Collectors.toList()));
        return quizDto;
    }

    private QuestionDTO convertQuestionToDto(Question question) {
        QuestionDTO questionDto = new QuestionDTO();
        questionDto.setId(question.getQuestionId());
        questionDto.setQuestionText(question.getQuestionText());
        return questionDto;
    }


    @GetMapping("/{quizId}")
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable Long quizId) {
        Quiz quiz = QuizRepository.findById(quizId).orElse(null);

        if (quiz != null) {
            QuizDTO quizDTO = mapQuizToDTO(quiz);
            return new ResponseEntity<>(quizDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Method to map a Quiz entity to a QuizDTO
    private QuizDTO mapQuizToDTO(Quiz quiz) {
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setId(quiz.getId());
        quizDTO.setTitle(quiz.getTitle());


        // Assuming you have a method to map questions
        quizDTO.setQuestions(mapQuestionsToDTO(quiz.getQuestions()));

        return quizDTO;
    }
    private List<QuestionDTO> mapQuestionsToDTO(List<Question> questions) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setId(question.getQuestionId());
            questionDTO.setQuestionText(question.getQuestionText());

            // Use the modified mapOptionsToDTO method
            questionDTO.setOptions(mapOptionsToDTO(question.getOptions()));

            questionDTOList.add(questionDTO);
        }

        return questionDTOList;
    }

    private List<optionsDTO> mapOptionsToDTO(List<Option> options) {
        List<optionsDTO> optionDTOList = new ArrayList<>();

        for (Option option : options) {
            optionsDTO optionDTO = new optionsDTO();
            optionDTO.setOptionId(option.getOptionId());
            optionDTO.setOptionText(option.getOptionText());
            optionDTO.setCorrectOption(option.isCorrectOption());

            optionDTOList.add(optionDTO);
        }

        return optionDTOList;
    }

    // Method to map a list of options



    // Endpoint to create a new quiz
    @PostMapping("/add")
    public ResponseEntity<Void> createQuiz(@RequestBody Quiz quiz) {
        QuizService.saveQuiz(quiz);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Endpoint to update an existing quiz
    @PutMapping("/{quizId}")
    public ResponseEntity<Void> updateQuiz(@PathVariable Long quizId, @RequestBody Quiz updatedQuiz) {
        Quiz existingQuiz = QuizService.getQuizById(quizId);

        if (existingQuiz != null) {
            // Set the ID of the existing quiz to ensure it's the same quiz being updated
            updatedQuiz.setId(quizId);
            QuizService.updateQuiz(updatedQuiz);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete a quiz by ID
    @DeleteMapping("/{quizId}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long quizId) {
        Quiz quiz = QuizService.getQuizById(quizId);
        if (quiz != null) {
            QuizService.deleteQuiz(quizId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }





}
