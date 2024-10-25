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
public class Course implements Serializable {
    @Id
    private String id;
    private String label;
    private String description;
    private EducationalProgram educationalProgram;
    //@DocumentReference(lookup = "{ '_id' : ?#{#target} }", lazy = false)
    @DBRef
    private List<Chapter> chapterList;

    public Course() {
        this.chapterList = new ArrayList<>();
    }

    public Course(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public Course(String id, String label, String description) {
        this.id = id;
        this.label = label;
        this.description = description;
    }

    public String getId() {
        return id;
    }
    public String getLabel() {
        return label;
    }
    public String getDescription() {
        return description;
    }

    public EducationalProgram getEducationalProgram() {
        return educationalProgram;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEducationalProgram(EducationalProgram educationalProgram) {
        this.educationalProgram = educationalProgram;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", educationalProgram=" + educationalProgram +
                '}';
    }
}
