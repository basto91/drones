package com.musala.drone.drone.repository;

import com.musala.drone.drone.repository.entity.Drone;
import com.musala.drone.drone.repository.enums.ModelEnum;
import com.musala.drone.drone.repository.enums.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone,Long>{

    List<Drone> findAllByStateEnumAndAndModelEnumAndBatteryCapacityGreaterThanEqual(StateEnum state, ModelEnum model, int batteryCapacity);
}
