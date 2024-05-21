package io.kamelbenarous.implementation;

import io.kamelbenarous.entity.Chapter;
import io.kamelbenarous.entity.Course;
import io.kamelbenarous.entity.EducationalProgram;
import io.kamelbenarous.repository.ChapterRepository;
import io.kamelbenarous.repository.CourseRepository;
import io.kamelbenarous.repository.EducationalProgramRepository;
import io.kamelbenarous.service.ICourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService implements ICourseService {
    private final ChapterRepository chapterRepository;
    private final CourseRepository courseRepository;
    private final EducationalProgramRepository educationalProgramRepository;

    public CourseService(
            ChapterRepository chapterRepository,
            CourseRepository courseRepository,
            EducationalProgramRepository educationalProgramRepository
    ) {
        this.chapterRepository = chapterRepository;
        this.courseRepository = courseRepository;
        this.educationalProgramRepository = educationalProgramRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return this.courseRepository.findAll();
    }

    @Override
    public Page<Course> getAllCoursesByPage(Pageable pageable, String searchLabel) {
        return courseRepository.findByLabelContaining(pageable, searchLabel);
    }

    @Override
    public Course getCourseById(String courseId) {
        return courseRepository.findById(courseId).orElseThrow(NullPointerException::new);
    }

    @Override
    public Course addCourse(Course course) {
        course.setId(UUID.randomUUID().toString());
        if(
            course.getChapterList() == null ||
            course.getChapterList().isEmpty() ||
            course.getChapterList().size() < 3
        ){
            Chapter chapter1 = new Chapter(
                course.getLabel() + " - introduction",
                    "introduction to the course : " + course.getLabel()
            );
            chapter1.setId(UUID.randomUUID().toString());
            chapter1.setCourse(course);

            Chapter chapter2 = new Chapter(
                    course.getLabel() + " - conclusion",
                    "conclusion to the course : " + course.getLabel()
            );
            chapter2.setId(UUID.randomUUID().toString());
            chapter2.setCourse(course);

            Chapter chapter3 = new Chapter(
                    course.getLabel() + " - resources",
                    "resources of the course : " + course.getLabel()
            );
            chapter3.setId(UUID.randomUUID().toString());
            chapter3.setCourse(course);

            List<Chapter> chapterList = new ArrayList<>();
            chapterList.add(chapter1);
            chapterList.add(chapter2);
            chapterList.add(chapter3);

            course.setChapterList(chapterList);

            chapterRepository.saveAll(chapterList);
        }else{
            course.getChapterList().stream().forEach(chapter -> {
                chapter.setCourse(course);
                chapterRepository.save(chapter);
            });
        }
        return courseRepository.save(course);
    }

    @Override
    public List<Course> addAllCourses(List<Course> courses) {
        for (Course course: courses) {
            this.addCourse(course);
        }
        return courses;
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public int deleteCourse(String courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course != null) {
            courseRepository.delete(course);
            return 1;
        }
        return 0;
    }

    @Override
    public int assignCourseToEducationalProgram(
            Course course, String educationalProgramId
    ) {
        EducationalProgram educationalProgram =
                educationalProgramRepository.findById(educationalProgramId).orElse(null);
        if(educationalProgram != null ) {
            educationalProgram.addCourseToEducationalProgram(course);
            educationalProgramRepository.save(educationalProgram);
            return 1;
        }
        return 0;
    }

}
