package com.conference.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Conference {

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "conference_participant",
            joinColumns = {@JoinColumn(name = "conference_id")},
            inverseJoinColumns = {@JoinColumn(name = "participant_id")})
    Set<Participant> participants = new HashSet<>();
    @Id
    @Column(name = "conference_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conferenceId;
    private String title;
    private String description;
    private Timestamp beginDate;
    private Timestamp endDate;
    private Integer maxSeats;


    public void addParticipant(Participant participant) {
        this.participants.add(participant);
        participant.getConferences().add(this);
    }

    public void removeParticipants(Long participantId) {
        Participant participant = this.participants.stream().filter(p -> Objects.equals(p.getParticipantId(), participantId)).findFirst().orElse(null);
        if (participant != null) {
            this.participants.remove(participant);
            participant.getConferences().remove(this);
        }
    }
}
