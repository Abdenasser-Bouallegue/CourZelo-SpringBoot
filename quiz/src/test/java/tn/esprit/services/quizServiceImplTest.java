package tn.esprit.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.entities.Option;
import tn.esprit.entities.Question;
import tn.esprit.entities.Quiz;
import tn.esprit.repositories.QuestionRepository;
import tn.esprit.repositories.quizRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class quizServiceImplTest {

    @Mock
    private quizRepository quizRepository;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private quizServiceImpl quizService;

    private Quiz quiz;
    private Question question;
    private Option option;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        option = new Option(); // Initialize Option if needed
        question = new Question(); // Initialize Question if needed
        question.setOptions(new ArrayList<>());
        question.getOptions().add(option); // Add option to question

        quiz = new Quiz();
        quiz.setId(1L);
        quiz.setTitle("Sample Quiz");
        quiz.setDescription("This is a sample quiz");
        quiz.setDuration(60);
        quiz.setQuestions(new ArrayList<>());
        quiz.getQuestions().add(question); // Add question to quiz
    }

    @Test
    void getQuizById() {
        when(quizRepository.findById(1L)).thenReturn(Optional.of(quiz));

        Quiz foundQuiz = quizService.getQuizById(1L);

        assertNotNull(foundQuiz);
        assertEquals(quiz.getId(), foundQuiz.getId());
        verify(quizRepository, times(1)).findById(1L);
    }

    @Test
    void saveQuiz() {
        when(quizRepository.save(quiz)).thenReturn(quiz);

        Quiz savedQuiz = quizService.saveQuiz(quiz);

        assertNotNull(savedQuiz);
        assertEquals(quiz.getTitle(), savedQuiz.getTitle());
        verify(quizRepository, times(1)).save(quiz);
    }

    @Test
    void updateQuiz() {
        when(quizRepository.save(quiz)).thenReturn(quiz);

        quizService.updateQuiz(quiz);

        verify(quizRepository, times(1)).save(quiz);
    }

    @Test
    void deleteQuiz() {
        doNothing().when(quizRepository).deleteById(1L);

        quizService.deleteQuiz(1L);

        verify(quizRepository, times(1)).deleteById(1L);
    }

    @Test
    void getAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(quiz);
        when(quizRepository.findAll()).thenReturn(quizzes);

        List<Quiz> foundQuizzes = quizService.getAllQuizzes();

        assertEquals(1, foundQuizzes.size());
        verify(quizRepository, times(1)).findAll();
    }
}
