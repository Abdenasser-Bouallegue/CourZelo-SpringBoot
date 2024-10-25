package io.kamelbenarous.implementation;

import io.kamelbenarous.entity.CalendarEvent;
import io.kamelbenarous.entity.Participant;
import io.kamelbenarous.repository.CalendarEventRepository;
import io.kamelbenarous.repository.ParticipantRepository;
import io.kamelbenarous.service.IParticipantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ParticipantServiceImpl implements IParticipantService {
    private ParticipantRepository participantRepository;
    private CalendarEventRepository calendarEventRepository;


    @Override
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    @Override
    public Participant getParticipantById(long id) {
        Optional<Participant> optionalParticipant = participantRepository.findById(id);
        return optionalParticipant.orElse(null);
    }

    @Override
    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    @Override
    public Participant updateParticipant(long id, Participant participant) {
        if (participantRepository.existsById(id)) {
            participant.setId(id);
            return participantRepository.save(participant);
        }
        return null;
    }

    @Override
    public void deleteParticipant(long id) {
        participantRepository.deleteById(id);
    }

    @Override
    public Participant addParticipantToEvent(long participantId, long eventId) {
        Participant participant = participantRepository.findById(participantId).orElse(null);
        CalendarEvent event = calendarEventRepository.findById(eventId).orElse(null);
        if (participant != null && event != null) {
            participant.getCalendarEvents().add(event);
            participantRepository.save(participant);
            return participant;
        }
        return null;
    }

    @Override
    public List<CalendarEvent> getParticipantEvents(long participantId) {
        Participant participant = participantRepository.findById(participantId).orElse(null);
        if (participant != null) {
            return participant.getCalendarEvents();
        }
        return null;
    }
}
