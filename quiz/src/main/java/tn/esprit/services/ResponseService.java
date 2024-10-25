package tn.esprit.services;

import tn.esprit.entities.AnswerDTO;

import java.util.List;

public interface ResponseService {
    int calculateScore(Long quizId, List<AnswerDTO> answers);
}
