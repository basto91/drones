package com.musala.drone.drone.repository.entity;

import com.musala.drone.drone.repository.enums.StateEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Entity(name="event")
@Data
@EqualsAndHashCode
@ToString
public class DronEvents extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private StateEnum state;

    @Column(name = "battery_capacity")
    @Range(min = 0, max = 100)
    private int batteryCapacity;

    @Column(name = "drone_id")
    private long droneId;


}
