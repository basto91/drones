package com.musala.drone.drone.service.dto;

import com.musala.drone.drone.repository.enums.ModelEnum;
import com.musala.drone.drone.repository.enums.StateEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class DroneResponse {

    private String serialNumber;
    private ModelEnum modelEnum;
    private long weight;
    private int batteryCapacity;
    private StateEnum stateEnum;

    private List<MedicationResponse> medications;
}
