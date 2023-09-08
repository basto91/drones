package com.musala.drone.drone.service.dto;

import com.musala.drone.drone.repository.entity.Medication;
import com.musala.drone.drone.repository.enums.ModelEnum;
import com.musala.drone.drone.repository.enums.StateEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Data
@Builder
public class DroneRequest {
    @NotBlank
    private String serialNumber;
    @NotBlank
    private ModelEnum modelEnum;
    @NotBlank
    @Range(min = 0, max = 500, message = "the max weight is 500")
    private long weight;
    @NotBlank
    private int batteryCapacity;
    @NotBlank
    private StateEnum stateEnum;

}
