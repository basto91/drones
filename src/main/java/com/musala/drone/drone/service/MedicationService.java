package com.musala.drone.drone.service;

import com.musala.drone.drone.repository.entity.Medication;
import com.musala.drone.drone.service.dto.MedicationRequest;
import com.musala.drone.drone.service.dto.MedicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicationService {

    List<Medication> getMedicationByIds(List<Long> medicationIds);
    MedicationResponse createMedication(MedicationRequest medicationRequest);

    Page<Medication> getMedicationList(Pageable pageable);

    void removeMedication( long medicationId);
}
