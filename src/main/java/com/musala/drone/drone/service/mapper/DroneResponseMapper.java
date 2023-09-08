package com.musala.drone.drone.service.mapper;

import com.musala.drone.drone.repository.entity.Drone;
import com.musala.drone.drone.service.dto.DroneResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DroneResponseMapper implements Mapper<Drone, DroneResponse> {

    private final MedicationResponseMapper medicationResponseMapper;
    @Override
    public DroneResponse entityToObject(Drone entity) {
        return DroneResponse.builder().batteryCapacity(entity.getBatteryCapacity())
                .modelEnum(entity.getModelEnum())
                .stateEnum(entity.getStateEnum())
                .weight(entity.getWeight())
                .id(entity.getId())
                .medications(entity.getItems()!=null?medicationResponseMapper.entitiesToObjectList(entity.getItems()):new ArrayList<>())
                .build();
    }

    @Override
    public Drone objectToEntity(DroneResponse object) {
        return null;
    }

    @Override
    public List<DroneResponse> entitiesToObjectList(List<Drone> entities) {
        return entities.stream().map(this::entityToObject).collect(Collectors.toList());
    }

    @Override
    public List<Drone> objectsToEntietiesList(List<DroneResponse> objects) {
        return null;
    }
}
