package com.musala.drone.drone.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Entity(name="medication")
@Data
@ToString
@EqualsAndHashCode
public class Medication implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "medication_id")
    private Long id;

    @Column(name="name")
    private String name;
    @Column(name = "weight")
    private long weight;
    @Column(name = "code")
    private String code;
    @Column(name="image")
    private String image;
    @ManyToOne
    @JoinColumn(name = "drone_id", insertable = false, updatable = false)
    private Drones drones;


}
