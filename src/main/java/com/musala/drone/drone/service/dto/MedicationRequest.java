package com.musala.drone.drone.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicationRequest {
    @Pattern(regexp = "^[\\w-]+$")
    @NotBlank(message = "Valid name is required")
    private String name;

    @NotNull(message = "Weight is required")
    private long weight;

    @Pattern(regexp = "^[A-Z0-9_]{11,15}$")
    @NotBlank(message = "Valid code is required")
    private String code;

    private String image;
}
