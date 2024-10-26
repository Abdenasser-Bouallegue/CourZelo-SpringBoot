package io.kamelbenarous.dto;

import io.kamelbenarous.entity.ResourceType;

public class ResourceMetaDataDTO {
    private String context;
    private ResourceType type;
    private String chapterId;

    public String getContext() {
        return context;
    }

    public ResourceType getType() {
        return type;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }
}
