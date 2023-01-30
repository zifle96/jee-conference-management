package com.conference.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Conference {
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "conferences", targetEntity = Participant.class)
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

    public Conference(Long conferenceId) {
        this.conferenceId = conferenceId;
    }
}
