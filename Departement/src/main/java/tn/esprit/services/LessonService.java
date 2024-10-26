package tn.esprit.services;

import tn.esprit.entities.Lesson;

import java.util.List;

public interface LessonService {



    List<Lesson> getAllLessons();
    Lesson getLessonById(Long id);
    Lesson createLesson(Lesson lesson);
    void deleteLessonById(Long id);
}
