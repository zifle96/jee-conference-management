package com.conference.management.repository;

import com.conference.management.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Query("SELECT p FROM Participant p JOIN FETCH conferences WHERE conferenceId= ?1")
    List<Participant> getByConferenceId(Long conferenceId);

}
