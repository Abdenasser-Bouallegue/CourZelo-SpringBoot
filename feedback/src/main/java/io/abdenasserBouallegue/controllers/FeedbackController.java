package io.abdenasserBouallegue.controllers;


import io.abdenasserBouallegue.entities.Feedback;
import io.abdenasserBouallegue.services.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/feedback")
public class FeedbackController {
    FeedbackService feedbackService;

    @GetMapping("/getAllFeedback")
    public Iterable<Feedback> getFeedbacks() {
        return feedbackService.retrieveAllFeedback();
    }

    @PostMapping("/addFeedback")
    public Feedback addFeedback(@RequestBody Feedback f) {
        return feedbackService.addFeedback(f);

    }

    @DeleteMapping("/remove/{feedback-id}")
    public void removeFeedback(@PathVariable("feedback-id") Long idFeedback) {
        feedbackService.removeFeedback(idFeedback);
    }

    @GetMapping("/{id}")
    public Feedback getFeedbackByID(@PathVariable Long id) {
        return feedbackService.retrieveFeedback(id);
    }

    @PutMapping("/SetArchive/{id}")
    public void ArchiveFeedback(@PathVariable Long id) {
        feedbackService.archiverFeedback(id);

    }

    @PutMapping("/UnArchived/{id}")
    public void unarchivedFeedback(@PathVariable Long id) {
        feedbackService.unarchivedFeedback(id);

    }



    @PutMapping("/forward/{id}")
    public void forwardFeedback(@PathVariable Long id) {
        feedbackService.forwardFeedback(id);
    }
}
