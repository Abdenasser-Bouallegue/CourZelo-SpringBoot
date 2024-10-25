package io.abdenasserBouallegue.servicesImpl;

import io.abdenasserBouallegue.entities.Feedback;
import io.abdenasserBouallegue.repositories.FeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceImplTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    private Feedback feedback;

    @BeforeEach
    void setUp() {
        feedback = Feedback.builder()
                .idFeedback(1L)
                .title("Test Feedback")
                .body("This is a test feedback")
                .CreatedAT(LocalDateTime.now())
                .archived(false)
                .user_id(1)
                .forward_to_user(0)
                .build();
    }

    @Test
    void testAddFeedback() {
        when(feedbackRepository.save(feedback)).thenReturn(feedback);

        Feedback savedFeedback = feedbackService.addFeedback(feedback);

        assertNotNull(savedFeedback);
        assertEquals(feedback.getTitle(), savedFeedback.getTitle());
        verify(feedbackRepository, times(1)).save(feedback);
    }

    @Test
    void testRetrieveFeedback() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));

        Feedback retrievedFeedback = feedbackService.retrieveFeedback(1L);

        assertNotNull(retrievedFeedback);
        assertEquals(feedback.getIdFeedback(), retrievedFeedback.getIdFeedback());
        verify(feedbackRepository, times(1)).findById(1L);
    }

    @Test
    void testRetrieveAllFeedback() {
        when(feedbackRepository.findAll()).thenReturn(Collections.singletonList(feedback));

        List<Feedback> feedbackList = feedbackService.retrieveAllFeedback();

        assertEquals(1, feedbackList.size());
        verify(feedbackRepository, times(1)).findAll();
    }

    @Test
    void testRemoveFeedback() {
        doNothing().when(feedbackRepository).deleteById(1L);

        feedbackService.removeFeedback(1L);

        verify(feedbackRepository, times(1)).deleteById(1L);
    }

    @Test
    void testArchiverFeedback() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));
        feedback.setArchived(true);
        when(feedbackRepository.save(feedback)).thenReturn(feedback);

        feedbackService.archiverFeedback(1L);

        assertTrue(feedback.isArchived());
        verify(feedbackRepository, times(1)).findById(1L);
        verify(feedbackRepository, times(1)).save(feedback);
    }

    @Test
    void testUnarchivedFeedback() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));
        feedback.setArchived(false);
        when(feedbackRepository.save(feedback)).thenReturn(feedback);

        feedbackService.unarchivedFeedback(1L);

        assertFalse(feedback.isArchived());
        verify(feedbackRepository, times(1)).findById(1L);
        verify(feedbackRepository, times(1)).save(feedback);
    }

    @Test
    void testForwardFeedback() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));
        feedback.setForward_to_user(2);
        when(feedbackRepository.save(feedback)).thenReturn(feedback);

        feedbackService.forwardFeedback(1L);

        assertEquals(2, feedback.getForward_to_user());
        verify(feedbackRepository, times(1)).findById(1L);
        verify(feedbackRepository, times(1)).save(feedback);
    }
}
