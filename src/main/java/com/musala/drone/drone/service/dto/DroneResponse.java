package com.musala.drone.drone.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musala.drone.drone.repository.enums.ModelEnum;
import com.musala.drone.drone.repository.enums.StateEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DroneResponse {
    private String serialNumber;
    private ModelEnum modelEnum;
    private long weight;
    private int batteryCapacity;
    private StateEnum stateEnum;
    private List<MedicationResponse> medications;
    private long id;
}
