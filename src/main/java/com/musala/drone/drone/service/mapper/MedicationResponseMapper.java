package com.musala.drone.drone.service.mapper;

import com.musala.drone.drone.repository.entity.Medication;
import com.musala.drone.drone.service.dto.MedicationResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicationResponseMapper implements Mapper<Medication, MedicationResponse>{

    @Override
    public MedicationResponse entityToObject(Medication entity) {
        return MedicationResponse.builder().image(entity.getImage()).name(entity.getName()).code(entity.getCode()).build();
    }

    @Override
    public Medication objectToEntity(MedicationResponse object) {
        Medication medication = new Medication();
        medication.setCode(object.getCode());
        medication.setName(object.getName());
        medication.setWeight(object.getWeight());
        return medication;
    }

    @Override
    public List<MedicationResponse> entitiesToObjectList(List<Medication> entities) {
        return entities.stream().map(this::entityToObject).collect(Collectors.toList());
    }

    @Override
    public List<Medication> objectsToEntietiesList(List<MedicationResponse> objects) {
        return objects.stream().map(this::objectToEntity).collect(Collectors.toList());
    }
}
