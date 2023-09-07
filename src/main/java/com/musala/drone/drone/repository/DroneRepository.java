package com.musala.drone.drone.repository;

import com.musala.drone.drone.repository.entity.Drones;
import com.musala.drone.drone.repository.enums.ModelEnum;
import com.musala.drone.drone.repository.enums.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drones,Long>{

    List<Drones> findAllByStateEnumAndAndModelEnumAndBatteryCapacityGreaterThanEqual(StateEnum state, ModelEnum model, int batteryCapacity);
}
