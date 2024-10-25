package tn.esprit.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entities.Lesson;
@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
}
