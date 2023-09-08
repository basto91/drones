package com.musala.drone.drone.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicationRequest {
    @Pattern(regexp = "^[\\w-]+$")
    @NotBlank(message = "Valid name is required")
    private String name;

    @NotNull(message = "Weight is required")
    private long weight;

    @Pattern(regexp = "^[A-Z0-9_]+$")
    @NotBlank(message = "Valid code is required")
    private String code;

    @JsonIgnore
    private MultipartFile image;

    private long id;
}
