package com.musala.drone.drone.service.mapper;

import com.musala.drone.drone.repository.MedicationRepository;
import com.musala.drone.drone.repository.entity.Medication;
import com.musala.drone.drone.service.FileSystemStorageService;
import com.musala.drone.drone.service.MedicationService;
import com.musala.drone.drone.service.dto.MedicationRequest;
import com.musala.drone.drone.service.dto.MedicationResponse;
import com.musala.drone.drone.util.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MedicationServiceImpl implements MedicationService {
    private final MedicationRepository medicationRepository;

    private final MedicationResponseMapper medicationResponseMapper;

    private final MedicationRequestMapper medicationRequestMapper;

    private  final FileSystemStorageService fileSystemStorageService;

    public MedicationServiceImpl(MedicationRepository medicationRepository, MedicationResponseMapper medicationResponseMapper, MedicationRequestMapper medicationRequestMapper, FileSystemStorageService fileSystemStorageService) {
        this.medicationRepository = medicationRepository;
        this.medicationResponseMapper = medicationResponseMapper;
        this.medicationRequestMapper = medicationRequestMapper;
        this.fileSystemStorageService = fileSystemStorageService;
    }

    @Override
    public List<Medication> getMedicationByIds(List<Long> medicationIds) {
        return medicationRepository.findAllByIdIn(medicationIds);
    }

    @Override
    public MedicationResponse createMedication(MedicationRequest medicationRequest) {
       Medication medication = medicationRequestMapper.objectToEntity(medicationRequest);
       medication= medicationRepository.save(medication);
       if (medicationRequest.getImage() != null){
           fileSystemStorageService.store(medicationRequest.getImage());
       }
        return MedicationResponse.builder().id(medication.getId())
                .image(medication.getImage())
                .name(medication.getName())
                .code(medication.getCode())
                .weight(medication.getWeight())
                .build();
    }

    @Override
    public Page getMedicationList(Pageable pageable) {
       return medicationRepository.findAll(pageable);
    }

    @Override
    public void removeMedication(long medicationId) {

        medicationRepository.deleteById(medicationId);
    }

    @Override
    public MedicationResponse updateMedication(MedicationRequest medicationRequest) throws NotFoundException {
        Optional<Medication> current = medicationRepository.findById(medicationRequest.getId());
        if (current.isPresent()){

        }else{
            throw new NotFoundException("Medication not found");
        }
        return null;
    }
}
