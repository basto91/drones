package com.musala.drone.drone.repository.entity;


import com.musala.drone.drone.repository.enums.ModelEnum;
import com.musala.drone.drone.repository.enums.StateEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Entity(name="drone")
@Data
@ToString
@EqualsAndHashCode
public class Drones  implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="drone_id")
    private Long id;

    @Size(max = 100)
    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "model")
    private ModelEnum modelEnum;

    @Column(name="weight")
    private long weight;

    @Column(name="battery")
    private int batteryCapacity;

    @Column(name="state")
    private StateEnum stateEnum;

    @OneToMany
    @JoinColumn(name="drone_id")
    private List<Medication> items;

}