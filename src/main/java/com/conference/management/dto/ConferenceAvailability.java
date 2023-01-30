package com.conference.management.dto;

import lombok.Data;


@Data
public class ConferenceAvailability extends ConferenceRequest {

    Boolean available;
    Integer seatsAvailable;
}
