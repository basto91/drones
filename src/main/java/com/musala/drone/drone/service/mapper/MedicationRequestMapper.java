package com.musala.drone.drone.service.mapper;

import com.musala.drone.drone.repository.entity.Medication;
import com.musala.drone.drone.service.dto.MedicationRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicationRequestMapper  implements Mapper<Medication, MedicationRequest>{


    @Override
    public MedicationRequest entityToObject(Medication entity) {
        return MedicationRequest.builder()
                .image(entity.getImage())
                .name(entity.getName())
                .code(entity.getCode())
                .weight(entity.getWeight())
                .build();
    }

    @Override
    public Medication objectToEntity(MedicationRequest object) {
        return null;
    }

    @Override
    public List<MedicationRequest> entitiesToObjectList(List<Medication> entities) {
        return null;
    }

    @Override
    public List<Medication> objectsToEntietiesList(List<MedicationRequest> objects) {
        return null;
    }
}
