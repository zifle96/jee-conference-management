package com.conference.management.entity;

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
    @ManyToMany(targetEntity = Conference.class, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "participant_conference",
            joinColumns = {@JoinColumn(name = "participant_id")},
            inverseJoinColumns = {@JoinColumn(name = "conference_id")}
    )
    Set<Conference> conferences = new HashSet<>();
    @Id
    @Column(name = "participant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;
    private String fullName;
    private String email;
    private String phoneNumber;

}
