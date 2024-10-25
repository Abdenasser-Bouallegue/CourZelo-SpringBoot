package io.kamelbenarous.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.util.List;

@Document
public class EducationalProgram implements Serializable {

    @Id
    private String id;
    private String programName;
    @DocumentReference
    private List<Course> courseList;

    public EducationalProgram() {
    }

    public EducationalProgram(String programName) {
        this.programName = programName;
    }

    public EducationalProgram(String id, String programName) {
        this.id = id;
        this.programName = programName;
    }

    public String getId() {
        return id;
    }


    public String getProgramName() {
        return programName;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public void addCourseToEducationalProgram(Course course) {
        this.courseList.add(course);
    }
}
