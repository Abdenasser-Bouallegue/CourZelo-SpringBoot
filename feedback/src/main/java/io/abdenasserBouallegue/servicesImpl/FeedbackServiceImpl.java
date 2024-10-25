package io.abdenasserBouallegue.servicesImpl;

import io.abdenasserBouallegue.entities.Feedback;
import io.abdenasserBouallegue.repositories.FeedbackRepository;
import io.abdenasserBouallegue.services.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    FeedbackRepository feedbackRepository;
    @Override
    public Feedback addFeedback(Feedback f) {
        return feedbackRepository.save(f);
    }

    @Override
    public Feedback retrieveFeedback(long idFeedback) {
        return feedbackRepository.findById(idFeedback).get();
    }

    @Override
    public List<Feedback> retrieveAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public void removeFeedback(Long idFeedback) {
        feedbackRepository.deleteById(idFeedback);
    }

    @Override
    public void archiverFeedback(long idFeedback) {
        Feedback feedback = feedbackRepository.findById(idFeedback).get();
        feedback.setArchived(true);
        feedbackRepository.save(feedback);

    }

    @Override
    public void unarchivedFeedback(long idFeedback) {
        Feedback feedback = feedbackRepository.findById(idFeedback).get();
        feedback.setArchived(false);
        feedbackRepository.save(feedback);

    }

    @Override
    public void deleteFeedback(long idFeedback) {
        feedbackRepository.deleteById(idFeedback);
    }

    @Override
    public void forwardFeedback(long idFeedback) {
        Feedback feedback = feedbackRepository.findById(idFeedback).get();
        feedback.setForward_to_user(2);
        feedbackRepository.save(feedback);
    }
}
