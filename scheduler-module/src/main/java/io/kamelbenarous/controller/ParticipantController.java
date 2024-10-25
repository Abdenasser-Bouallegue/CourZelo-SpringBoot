package io.kamelbenarous.controller;

import io.kamelbenarous.entity.CalendarEvent;
import io.kamelbenarous.entity.Participant;
import io.kamelbenarous.service.IParticipantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ParticipantController {

    private IParticipantService participantService;

    @GetMapping("/participants")
    public List<Participant> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    @GetMapping("/participants/{id}")
    public Participant getParticipantById(@PathVariable long id) {
        return participantService.getParticipantById(id);
    }

    @PostMapping("/participants")
    public Participant createParticipant(@RequestBody Participant participant) {
        return participantService.createParticipant(participant);
    }

    @PutMapping("/participants/{id}")
    public Participant updateParticipant(@PathVariable long id, @RequestBody Participant participant) {
        return participantService.updateParticipant(id, participant);
    }

    @DeleteMapping("/participants/{id}")
    public void deleteParticipant(@PathVariable long id) {
        participantService.deleteParticipant(id);
    }

    @PutMapping("/participants/{participantId}/events/{eventId}")
    public Participant addParticipantToEvent(@PathVariable long participantId, @PathVariable long eventId) {
        return participantService.addParticipantToEvent(participantId, eventId);
    }

    @GetMapping("/participants/{participantId}/events")
    public List<CalendarEvent> getParticipantEvents(@PathVariable long participantId) {
        return participantService.getParticipantEvents(participantId);
    }
}
