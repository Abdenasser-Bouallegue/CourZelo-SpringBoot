package tn.esprit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.entities.AnswerDTO;
import tn.esprit.entities.QuizNotFoundException;
import tn.esprit.services.ResponseService;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class ReponseController {

    private final ResponseService responseService;

    @Autowired
    public ReponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @PostMapping("/{quizId}/submit")
    public ResponseEntity<Integer> submitQuizResponse(
            @PathVariable Long quizId,
            @RequestBody List<AnswerDTO> answers) {
        try {
            int score = responseService.calculateScore(quizId, answers);
            return ResponseEntity.ok(score);
        } catch (QuizNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
