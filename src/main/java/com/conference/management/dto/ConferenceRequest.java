package com.conference.management.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ConferenceRequest {

    private Long id;
    private String title;
    private String description;
    private Timestamp beginDate;
    private Timestamp endDate;
    private Integer maxSeats;
}
