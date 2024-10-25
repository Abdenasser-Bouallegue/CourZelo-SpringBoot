package io.kamelbenarous.dto;

public class JsonResponsePojo {
    private String message;

    public JsonResponsePojo() {
    }

    public JsonResponsePojo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
