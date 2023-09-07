package com.musala.drone.drone.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Entity(name="event")
@Data
@EqualsAndHashCode
@ToString
public class Events extends Auditable<String> implements Serializable {
    @Column(name="event_name")
    private String name;
    @Id
    @GeneratedValue
    private Long id;

}
