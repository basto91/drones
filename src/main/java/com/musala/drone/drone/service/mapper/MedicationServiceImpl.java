package com.musala.drone.drone.service.mapper;

import com.musala.drone.drone.repository.MedicationRepository;
import com.musala.drone.drone.repository.entity.Medication;
import com.musala.drone.drone.service.MedicationService;
import com.musala.drone.drone.service.dto.MedicationRequest;
import com.musala.drone.drone.service.dto.MedicationResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MedicationServiceImpl implements MedicationService {
    private final MedicationRepository medicationRepository;

    private final MedicationResponseMapper medicationResponseMapper;

    private final MedicationRequestMapper medicationRequestMapper;

    public MedicationServiceImpl(MedicationRepository medicationRepository, MedicationResponseMapper medicationResponseMapper, MedicationRequestMapper medicationRequestMapper) {
        this.medicationRepository = medicationRepository;
        this.medicationResponseMapper = medicationResponseMapper;
        this.medicationRequestMapper = medicationRequestMapper;
    }

    @Override
    public List<Medication> getMedicationByIds(List<Long> medicationIds) {
        return medicationRepository.findAllByIdIn(medicationIds);
    }

    @Override
    public MedicationResponse createMedication(MedicationRequest medicationRequest) {
       Medication medication = medicationRequestMapper.objectToEntity(medicationRequest);
       medication= medicationRepository.save(medication);
        return MedicationResponse.builder().image(medication.getImage()).name(medication.getName()).code(medication.getCode()).weight(medication.getWeight()).build();
    }

    @Override
    public Page<Medication> getMedicationList(Pageable pageable) {
       return medicationRepository.findAll(pageable);
    }

    @Override
    public void removeMedication(long medicationId) {
        medicationRepository.deleteById(medicationId);
    }
}
