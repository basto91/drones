package com.musala.drone.drone.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AuthenticationResponse implements Serializable {
    private String jwt;
    private String type;
}
