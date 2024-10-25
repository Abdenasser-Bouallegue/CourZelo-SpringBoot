package io.kamelbenarous.service;

import io.kamelbenarous.entity.CalendarEvent;
import io.kamelbenarous.entity.Participant;

import java.util.List;

public interface IParticipantService {
    List<Participant> getAllParticipants();
    Participant getParticipantById(long id);
    Participant createParticipant(Participant participant);
    Participant updateParticipant(long id, Participant participant);
    void deleteParticipant(long id);
    Participant addParticipantToEvent(long participantId, long eventId);
    List<CalendarEvent> getParticipantEvents(long participantId);

}
