package io.kamelbenarous.service;

import io.kamelbenarous.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ICourseService {
    List<Course> getAllCourses();
    Page<Course> getAllCoursesByPage(Pageable pageable, String seachLabel);

    Course getCourseById(String courseId);
    Course addCourse(Course course);
    List<Course> addAllCourses(List<Course> courses);
    Course updateCourse(Course course);
    int deleteCourse(String courseId);
    int assignCourseToEducationalProgram(Course course, String educationalProgramId);

}
