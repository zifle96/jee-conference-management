package com.conference.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParticipantRequest {

    private String fullName;
    private String email;
    private String phoneNumber;
}
