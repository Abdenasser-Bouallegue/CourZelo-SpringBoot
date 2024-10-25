package io.kamelbenarous.dto;

import io.kamelbenarous.entity.ResourceType;

public class ResourceDTO {
    private long id;
    private String context;
    private ResourceType type;
    private byte[] data;

    public ResourceDTO() {
    }

    public long getId() {
        return id;
    }

    public String getContext() {
        return context;
    }

    public ResourceType getType() {
        return type;
    }

    public byte[] getData() {
        return data;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setImage() {
        this.type = ResourceType.IMAGE;
    }

    public void setPDF(){
        this.type = ResourceType.PDF;
    }

    public void setVideo(){
        this.type = ResourceType.VIDEO;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

