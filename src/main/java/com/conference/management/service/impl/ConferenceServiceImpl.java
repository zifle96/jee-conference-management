package com.conference.management.service.impl;

import com.conference.management.dto.ConferenceAvailability;
import com.conference.management.dto.ConferenceRequest;
import com.conference.management.dto.ParticipantRequest;
import com.conference.management.entity.Conference;
import com.conference.management.entity.Participant;
import com.conference.management.repository.ConferenceRepository;
import com.conference.management.repository.ParticipantRepository;
import com.conference.management.service.ConferenceService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConferenceServiceImpl implements ConferenceService {

    @Autowired
    ConferenceRepository conferenceRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Override
    public Conference createConference(ConferenceRequest conferenceRequest) {
        Conference newConference = Conference.builder().title(conferenceRequest.getTitle())
                .description(conferenceRequest.getDescription()).maxSeats(conferenceRequest.getMaxSeats())
                .beginDate(conferenceRequest.getBeginDate())
                .endDate(conferenceRequest.getEndDate()).build();
        return conferenceRepository.save(newConference);
    }

    @Override
    public void cancelConference(Long confId) {
        conferenceRepository.deleteById(confId);
    }

    @Override
    public ConferenceAvailability getConferenceAvailability(Long confId) {
        Conference conference = conferenceRepository.getReferenceById(confId);
        int availableSeats = Math.toIntExact(Math.subtractExact(conference.getMaxSeats(), (long) conference.getParticipants().size()));
        ConferenceAvailability conferenceAvailability = new ConferenceAvailability();
        conferenceAvailability.setId(confId);
        conferenceAvailability.setTitle(conference.getTitle());
        conferenceAvailability.setDescription(conference.getDescription());
        conferenceAvailability.setBeginDate(conference.getBeginDate());
        conferenceAvailability.setEndDate(conference.getEndDate());
        conferenceAvailability.setMaxSeats(conference.getMaxSeats());
        conferenceAvailability.setAvailable(availableSeats > 0);

        conferenceAvailability.setSeatsAvailable(availableSeats);
        return conferenceAvailability;
    }

    @Override
    public Participant addParticipant(Long confId, ParticipantRequest participantRequest) {
        Optional<Conference> conference = conferenceRepository.findById(confId);
        if (conference.isPresent()) {
            Participant participant = new Participant();
            participant.setFullName(participantRequest.getFullName());
            participant.setEmail(participantRequest.getEmail());
            participant.setPhoneNumber(participant.getPhoneNumber());

            conference.get().addParticipant(participant);
            return participantRepository.save(participant);
        } else throw new ServiceException(String.format("Conference with id %s doesnt exist", confId));
    }

    @Override
    public void removeParticipants(Long participantId, Long confId) {
        Optional<Conference> conference = conferenceRepository.findById(confId);
        if (conference.isPresent()) {
            conference.get().removeParticipants(participantId);
            conferenceRepository.save(conference.get());
        } else throw new ServiceException(String.format("Conference with id %s doesnt exist", confId));
    }
}
