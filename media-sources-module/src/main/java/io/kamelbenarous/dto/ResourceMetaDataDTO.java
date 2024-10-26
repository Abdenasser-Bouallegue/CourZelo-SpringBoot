package io.kamelbenarous.dto;

import io.kamelbenarous.entity.MediaSourceType;

public class ResourceMetaDataDTO {
    private String context;
    private MediaSourceType type;
    private String chapterId;

    public String getContext() {
        return context;
    }

    public MediaSourceType getType() {
        return type;
    }

    public String getChapterId() {
        return chapterId;
    }
}
