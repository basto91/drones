package com.musala.drone.drone.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class DroneAvailableResponse implements Serializable {
    List<DroneResponse> availableDrone;
}
