package io.kamelbenarous.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document
public class Chapter implements Serializable {
    @Id
    private String id;
    private String title;
    private String description;
    private List<String> resourseRefList = new ArrayList<>();
    private Course course;

    public Chapter() {
    }

    public Chapter(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Chapter(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Chapter(String id, String title, String description, List<String> resourseRefList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.resourseRefList = resourseRefList;
    }

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public List<String> getResourseRefList() {
        return resourseRefList;
    }
    public Course getCourse() {
        return course;
    }



    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResourseRefList(List<String> resourseRefList) {
        this.resourseRefList = resourseRefList;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", resourseRefList=" + resourseRefList +
                ", course=" + course +
                '}';
    }
}
