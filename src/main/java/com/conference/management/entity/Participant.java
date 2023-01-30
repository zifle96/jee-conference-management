package com.conference.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Participant {

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "participants")
    @JsonIgnore
    Set<Conference> conferences = new HashSet<>();
    @Id
    @Column(name = "participant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;
    private String fullName;
    private String email;
    private String phoneNumber;

    public void addConference(Conference conference) {
        this.conferences.add(conference);
        conference.getParticipants().add(this);
    }

    public void removeConference(Conference conference) {
        this.conferences.remove(conference);
        conference.getParticipants().remove(this);
    }

}
