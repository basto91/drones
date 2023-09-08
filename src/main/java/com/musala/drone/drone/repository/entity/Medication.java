package com.musala.drone.drone.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Data
@ToString
@EqualsAndHashCode
public class Medication implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "medication_id")
    private Long id;

    @Pattern(regexp = "^[\\w-]+$")
    @NotBlank(message = "Valid name is required")
    @Column(name="name")
    private String name;

    @NotNull(message = "Weight is required")
    @Column(name = "weight")
    private long weight;

    @Pattern(regexp = "^[A-Z0-9_]+$")
    @NotBlank(message = "Valid code is required")
    @Column(name = "code")
    private String code;

    @Column(name="image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "drone_id", insertable = false, updatable = false)
    private Drone drone;


}
