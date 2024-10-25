package io.kamelbenarous.entity;

import jakarta.persistence.*;

@Entity
public class MediaSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String context;
    @Enumerated(EnumType.STRING)
    private MediaSourceType mediaSourceType;
    @Lob
    @Column(length = 536870912)
    private byte[] file;
    private String chapterId;

    public MediaSource() {
    }

    public MediaSource(long id, String context, MediaSourceType mediaSourceType, byte[] file, String chapterId) {
        this.id = id;
        this.context = context;
        this.mediaSourceType = mediaSourceType;
        this.file = file;
        this.chapterId = chapterId;
    }

    public long getId() {
        return id;
    }

    public String getContext() {
        return context;
    }

    public MediaSourceType getMediaSourceType() {
        return mediaSourceType;
    }

    public byte[] getFile() {
        return file;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setMediaSourceType(MediaSourceType mediaSourceType) {
        this.mediaSourceType = mediaSourceType;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }
}
