package io.kamelbenarous.repository;

import io.kamelbenarous.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
    Page<Course> findByLabelContaining(Pageable pageable, String label);
}
