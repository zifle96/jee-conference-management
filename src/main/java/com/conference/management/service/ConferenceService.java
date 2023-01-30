package com.conference.management.service;

import com.conference.management.dto.ConferenceAvailability;
import com.conference.management.dto.ConferenceRequest;
import com.conference.management.dto.ParticipantRequest;
import com.conference.management.entity.Conference;
import com.conference.management.entity.Participant;
import org.springframework.stereotype.Service;

@Service
public interface ConferenceService {

    Conference createConference(ConferenceRequest conferenceRequest);

    void cancelConference(Long confId);

    ConferenceAvailability getConferenceAvailability(Long confId);

    Participant addParticipant(Long confId, ParticipantRequest participant);

    void removeParticipants(Long participantId, Long confId);
}
