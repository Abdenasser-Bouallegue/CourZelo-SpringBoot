package io.abdenasserBouallegue.services;


import io.abdenasserBouallegue.entities.Feedback;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FeedbackService {
    Feedback addFeedback(Feedback f);
    Feedback retrieveFeedback(long idFeedback);
    List<Feedback> retrieveAllFeedback();
    void removeFeedback(Long idFeedback);
    void archiverFeedback(long idFeedback);
    void unarchivedFeedback(long idFeedback);
    void deleteFeedback (long idFeedback);
    void forwardFeedback(long idFeedback);
}
