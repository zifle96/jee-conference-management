package com.conference.management.resource;

import com.conference.management.dto.ConferenceAvailability;
import com.conference.management.dto.ConferenceRequest;
import com.conference.management.entity.Conference;
import com.conference.management.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conferences")
public class ConferenceResource {

    @Autowired
    ConferenceService conferenceService;

    @GetMapping(value = "{confId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ConferenceAvailability getConferenceAvailability(@PathVariable Long confId) {
        return conferenceService.getConferenceAvailability(confId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Conference createConference(@RequestBody ConferenceRequest conferenceRequest) {
        return conferenceService.createConference(conferenceRequest);
    }


    @DeleteMapping("/{confId}")
    public void cancelConference(@PathVariable Long confId) {
        conferenceService.cancelConference(confId);
    }

}
