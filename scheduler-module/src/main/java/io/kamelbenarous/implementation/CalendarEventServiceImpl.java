package io.kamelbenarous.implementation;

import io.kamelbenarous.entity.CalendarEvent;
import io.kamelbenarous.repository.CalendarEventRepository;
import io.kamelbenarous.service.ICalendarEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CalendarEventServiceImpl implements ICalendarEventService {
    private CalendarEventRepository calendarEventRepository;

    @Override
    public List<CalendarEvent> getAllCalendarEvents() {
        return calendarEventRepository.findAll();
    }

    @Override
    public CalendarEvent getCalendarEventById(long id) {
        Optional<CalendarEvent> optionalCalendarEvent = calendarEventRepository.findById(id);
        return optionalCalendarEvent.orElse(null);
    }

    @Override
    public CalendarEvent createCalendarEvent(CalendarEvent calendarEvent) {
        return calendarEventRepository.save(calendarEvent);
    }

    @Override
    public CalendarEvent updateCalendarEvent(long id, CalendarEvent calendarEvent) {
        if (calendarEventRepository.existsById(id)) {
            calendarEvent.setCalendarEventId(id);
            return calendarEventRepository.save(calendarEvent);
        }
        return null;
    }

    @Override
    public void deleteCalendarEvent(long id) {
        calendarEventRepository.deleteById(id);
    }
}
