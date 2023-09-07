package com.musala.drone.drone.service.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class DroneMedicationResponse {
    private long droneId;
    private List<MedicationResponse> medications;
}
