package io.kamelbenarous.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChapterDTO implements Serializable {
    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("resourcesRefList")
    private List<String> resourcesRefList = new ArrayList<>();

    public ChapterDTO() {

    }

    public ChapterDTO(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    public void addResource(String resourceRef) {
        this.resourcesRefList.add(resourceRef);
    }

    public void setResources(List<String> ls) {
        this.resourcesRefList = new ArrayList<>(ls);
    }

    @Override
    public String toString() {
        return "ChapterDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", resourcesRefList=" + resourcesRefList +
                '}';
    }
}
