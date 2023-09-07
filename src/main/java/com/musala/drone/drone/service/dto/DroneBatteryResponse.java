package com.musala.drone.drone.service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DroneBatteryResponse {
    private int batteryLevel;
}
