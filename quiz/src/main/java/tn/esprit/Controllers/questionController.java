package tn.esprit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.entities.Question;
import tn.esprit.services.QuestionService;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/questions")
public class questionController {
    @Autowired
    private QuestionService questionService;

    // Endpoint to get all questions


    // Endpoint to get a specific question by ID
    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long questionId) {
        Question question = questionService.getQuestionById(questionId);
        if (question != null) {
            return new ResponseEntity<>(question, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to create a new question
    @PostMapping
    public ResponseEntity<Void> createQuestion(@RequestBody Question question) {
        questionService.saveQuestion(question);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Endpoint to update an existing question
    @PutMapping("/{questionId}")
    public ResponseEntity<Void> updateQuestion(@PathVariable Long questionId, @RequestBody Question updatedQuestion) {
        Question existingQuestion = questionService.getQuestionById(questionId);

        if (existingQuestion != null) {
            // Set the ID of the existing question to ensure it's the same question being updated
            updatedQuestion.setId(questionId);
            questionService.updateQuestion(updatedQuestion);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete a question by ID
    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        Question question = questionService.getQuestionById(questionId);
        if (question != null) {
            questionService.deleteQuestion(questionId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
