package io.kamelbenarous.controller;

import io.kamelbenarous.dto.CourseDTO;
import io.kamelbenarous.dto.JsonResponsePojo;
import io.kamelbenarous.entity.Chapter;
import io.kamelbenarous.entity.Course;
import io.kamelbenarous.implementation.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController("/courses")
@CrossOrigin(value = "*", exposedHeaders = {"totalPageNumber"})
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses/getall")
    public ResponseEntity<List<CourseDTO>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int sizePerPage,
            @RequestParam(name = "searchLabel", defaultValue = "") String searchLabel
    ) {
        Pageable pageable = PageRequest.of(page, sizePerPage);
        log.info("getting all courses");
        log.info(searchLabel);
        CourseDTO dto;
        Page<Course> coursePage =  this.courseService.getAllCoursesByPage(pageable, searchLabel);
        List<CourseDTO> ls = new ArrayList<>();
        for (Course course: coursePage){
            dto  = new CourseDTO();
            log.info(course.toString());
            dto.setId(course.getId());
            dto.setLabel(course.getLabel());
            dto.setDescription(course.getDescription());
            ls.add(dto);
            log.info(dto.toString());
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .headers(httpHeaders -> httpHeaders.add("totalPageNumber", Integer.toString(coursePage.getTotalPages())))
                .body(ls);
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<CourseDTO> getCourseById(
            @PathVariable("courseId") String courseId
    ) {

        CourseDTO dto = new CourseDTO();
        try {
            log.info("getting course " + courseId);
            Course course = courseService.getCourseById(courseId);
            log.info("got course " + courseId);

            dto.setId(course.getId());
            dto.setLabel(course.getLabel());
            dto.setDescription(course.getDescription());
            for (Chapter chapter: course.getChapterList()) {
                log.info(chapter.getId() + " " + chapter.getTitle());
                dto.addChapter(chapter);
            }
            return ResponseEntity
                    .status(HttpStatus.SC_OK)
                    .body(dto);
        } catch(NullPointerException nullPointerException) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        }
    }

    @PostMapping("/courses/add")
    public ResponseEntity<JsonResponsePojo> addCourse(@RequestBody Course course){
        Course c = this.courseService.addCourse(course);
        JsonResponsePojo pojo = new JsonResponsePojo();
        if(c != null) {
            pojo.setMessage("course created");
            return ResponseEntity
                    .status(HttpStatus.SC_CREATED)
                    .body(pojo);
        } else {
            pojo.setMessage("can not create course");
            return ResponseEntity
                    .status(HttpStatus.SC_UNAUTHORIZED)
                    .body(pojo);
        }
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<JsonResponsePojo> deleteCourse(
            @PathVariable("courseId") String courseId
    ){
        courseService.deleteCourse(courseId);
        JsonResponsePojo pojo = new JsonResponsePojo();
        int courseDeleted = courseService.deleteCourse(courseId);
        if(courseDeleted != 0) {
            pojo.setMessage("course deleted");
            return ResponseEntity
                    .status(HttpStatus.SC_CREATED)
                    .body(pojo);
        } else {
            pojo.setMessage("can not delete course");
            return ResponseEntity
                    .status(HttpStatus.SC_UNAUTHORIZED)
                    .body(pojo);
        }
    }
}
