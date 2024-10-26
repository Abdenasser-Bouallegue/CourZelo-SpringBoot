package io.kamelbenarous.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kamelbenarous.entity.Chapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseDTO implements Serializable {
    @JsonProperty("id")
    private String id;
    @JsonProperty("label")
    private String label;
    @JsonProperty("description")
    private String description;
    @JsonProperty("chapters")
    private List<ChapterDTO> chapterDTOList = new ArrayList<>();
    public CourseDTO() {
    }

    public CourseDTO(String id, String label, String description) {
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

    public List<ChapterDTO> getChapterDTOList() {
        return chapterDTOList;
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

    public void setChapterDTOList(List<ChapterDTO> chapterDTOList) {
        this.chapterDTOList = chapterDTOList;
    }

    public void addChapter(Chapter chapter){
        ChapterDTO dto = new ChapterDTO();
        dto.setId(chapter.getId());
        dto.setTitle(chapter.getTitle());
        dto.setDescription(chapter.getDescription());
        if (chapter.getResourseRefList() != null )
            dto.setResources(chapter.getResourseRefList());
        this.chapterDTOList.add(dto);
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", chapterDTOList=" + chapterDTOList +
                '}';
    }
}
