package com.musala.drone.drone.service.mapper;

import com.musala.drone.drone.repository.entity.Drones;
import com.musala.drone.drone.service.dto.DroneRequest;

import java.util.List;

public class DroneRequestMapper implements Mapper<Drones, DroneRequest>{

    @Override
    public DroneRequest entityToObject(Drones entity) {
        return null;
    }

    @Override
    public Drones objectToEntity(DroneRequest object) {
        Drones drones = new Drones();
        drones.setWeight(object.getWeight());
        drones.setSerialNumber(object.getSerialNumber());
        drones.setBatteryCapacity(object.getBatteryCapacity());
        drones.setModelEnum(object.getModelEnum());
        drones.setStateEnum(object.getStateEnum());
        return drones;
    }

    @Override
    public List<DroneRequest> entitiesToObjectList(List<Drones> entities) {
        return null;
    }

    @Override
    public List<Drones> objectsToEntietiesList(List<DroneRequest> objects) {
        return null;
    }
}
