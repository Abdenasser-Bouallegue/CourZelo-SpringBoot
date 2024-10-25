package io.kamelbenarous.controller;

import io.kamelbenarous.entity.CalendarEvent;
import io.kamelbenarous.service.ICalendarEventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class CalendarEventController {

    private ICalendarEventService calendarEventService;

    @GetMapping("/calendar-events")
    public List<CalendarEvent> getAllCalendarEvents() {
        return calendarEventService.getAllCalendarEvents();
    }

    @GetMapping("/calendar-events/{id}")
    public CalendarEvent getCalendarEventById(@PathVariable long id) {
        return calendarEventService.getCalendarEventById(id);
    }

    @PostMapping("/calendar-events")
    public CalendarEvent createCalendarEvent(@RequestBody CalendarEvent calendarEvent) {
        return calendarEventService.createCalendarEvent(calendarEvent);
    }

    @PutMapping("/calendar-events/{id}")
    public CalendarEvent updateCalendarEvent(@PathVariable long id, @RequestBody CalendarEvent calendarEvent) {
        return calendarEventService.updateCalendarEvent(id, calendarEvent);
    }

    @DeleteMapping("/calendar-events/{id}")
    public void deleteCalendarEvent(@PathVariable long id) {
        calendarEventService.deleteCalendarEvent(id);
    }
}
