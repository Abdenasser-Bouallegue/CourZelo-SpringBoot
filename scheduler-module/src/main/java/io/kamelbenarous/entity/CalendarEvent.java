package io.kamelbenarous.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class CalendarEvent implements Serializable {

    @Id
    @GeneratedValue
    private long calendarEventId;
    private String label;
    private String description;
    private boolean isPrivate;
    private String beginTime;
    private String endTime;
    @ManyToMany
    private List<Participant> participants = new ArrayList<>();

    public CalendarEvent() {
    }

    public CalendarEvent(String label, String description, boolean isPrivate, String beginTime, String endTime) {
        this.label = label;
        this.description = description;
        this.isPrivate = isPrivate;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public CalendarEvent(
            long calendarEventId,
            String label,
            String description,
            boolean isPrivate,
            String beginTime,
            String endTime,
            List<Participant> participants
    ) {
        this.calendarEventId = calendarEventId;
        this.label = label;
        this.description = description;
        this.isPrivate = isPrivate;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.participants = participants;
    }

    public long getCalendarEventId() {
        return calendarEventId;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getEndTime() {
        return endTime;
    }


    public List<Participant> getParticipants() {
        return participants;
    }

    public void setCalendarEventId(long calendarEventId) {
        this.calendarEventId = calendarEventId;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
}
