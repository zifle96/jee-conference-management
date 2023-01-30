package com.conference.management.resource;


import com.conference.management.dto.ParticipantRequest;
import com.conference.management.entity.Participant;
import com.conference.management.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participants")
public class ParticipantResource {

    @Autowired
    ConferenceService conferenceService;

    @PostMapping(value = "/conferences/{confId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Participant addParticipants(@PathVariable Long confId, @RequestBody ParticipantRequest participantRequest) {
        return conferenceService.addParticipant(confId, participantRequest);
    }

    @DeleteMapping(value = "{participantId}/conferences/{confId}")
    public void removeParticipants(@PathVariable Long participantId, @PathVariable Long confId) {
        conferenceService.removeParticipants(participantId, confId);

    }
}
