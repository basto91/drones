package com.musala.drone.drone.service.mapper;

import com.musala.drone.drone.repository.entity.Drone;
import com.musala.drone.drone.service.dto.DroneRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DroneRequestMapper implements Mapper<Drone, DroneRequest>{

    @Override
    public DroneRequest entityToObject(Drone entity) {
        return null;
    }

    @Override
    public Drone objectToEntity(DroneRequest object) {
        Drone drone = new Drone();
        drone.setWeight(object.getWeight());
        drone.setSerialNumber(object.getSerialNumber());
        drone.setBatteryCapacity(object.getBatteryCapacity());
        drone.setModelEnum(object.getModelEnum());
        drone.setStateEnum(object.getStateEnum());
        return drone;
    }

    @Override
    public List<DroneRequest> entitiesToObjectList(List<Drone> entities) {
        return null;
    }

    @Override
    public List<Drone> objectsToEntietiesList(List<DroneRequest> objects) {
        return null;
    }
}
