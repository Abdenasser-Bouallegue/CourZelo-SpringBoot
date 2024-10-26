package io.kamelbenarous.service;

import io.kamelbenarous.entity.CalendarEvent;

import java.util.List;

public interface ICalendarEventService {
    List<CalendarEvent> getAllCalendarEvents();
    CalendarEvent getCalendarEventById(long id);
    CalendarEvent createCalendarEvent(CalendarEvent calendarEvent);
    CalendarEvent updateCalendarEvent(long id, CalendarEvent calendarEvent);
    void deleteCalendarEvent(long id);
}
