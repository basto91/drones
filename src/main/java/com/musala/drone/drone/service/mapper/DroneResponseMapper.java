package com.musala.drone.drone.service.mapper;

import com.musala.drone.drone.repository.entity.Drones;
import com.musala.drone.drone.service.dto.DroneResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DroneResponseMapper implements Mapper<Drones, DroneResponse> {

    private final MedicationResponseMapper medicationResponseMapper;
    @Override
    public DroneResponse entityToObject(Drones entity) {
        return DroneResponse.builder().batteryCapacity(entity.getBatteryCapacity())
                .modelEnum(entity.getModelEnum())
                .stateEnum(entity.getStateEnum())
                .weight(entity.getWeight())
                .medications(medicationResponseMapper.entitiesToObjectList(entity.getItems()))
                .build();
    }

    @Override
    public Drones objectToEntity(DroneResponse object) {
        return null;
    }

    @Override
    public List<DroneResponse> entitiesToObjectList(List<Drones> entities) {
        return entities.stream().map(this::entityToObject).collect(Collectors.toList());
    }

    @Override
    public List<Drones> objectsToEntietiesList(List<DroneResponse> objects) {
        return null;
    }
}
