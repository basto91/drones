package com.musala.drone.drone.service;

import com.musala.drone.drone.repository.DroneRepository;
import com.musala.drone.drone.repository.entity.Drones;
import com.musala.drone.drone.repository.entity.Medication;
import com.musala.drone.drone.repository.enums.ModelEnum;
import com.musala.drone.drone.repository.enums.StateEnum;
import com.musala.drone.drone.service.dto.*;
import com.musala.drone.drone.service.mapper.DroneRequestMapper;
import com.musala.drone.drone.service.mapper.DroneResponseMapper;
import com.musala.drone.drone.service.mapper.MedicationResponseMapper;
import com.musala.drone.drone.util.exceptions.FullFleetException;
import com.musala.drone.drone.util.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DroneServiceImpl implements DroneService{

    private final DroneRepository droneRepository;
    private final DroneResponseMapper droneResponseMapper;

    private final DroneRequestMapper droneRequestMapper;

    private final MedicationResponseMapper medicationResponseMapper;

    private final MedicationService medicationService;




    public DroneServiceImpl(DroneRepository droneRepository, DroneResponseMapper droneResponseMapper, DroneRequestMapper droneRequestMapper, MedicationResponseMapper medicationResponseMapper, MedicationService medicationService) {
        this.droneRepository = droneRepository;
        this.droneResponseMapper = droneResponseMapper;
        this.droneRequestMapper = droneRequestMapper;
        this.medicationResponseMapper = medicationResponseMapper;
        this.medicationService = medicationService;
    }

    @Override
    public DroneResponse registerDrone(DroneRequest droneRequest) throws FullFleetException {
        Optional<Long> dronesCount=Optional.ofNullable(droneRepository.count()) ;
        if(dronesCount.isPresent() && dronesCount.get()<10 ) {
            Drones drones =droneRequestMapper.objectToEntity(droneRequest);
            Drones savedDrone= droneRepository.save(drones);
            return droneResponseMapper.entityToObject(savedDrone);
        }else{

             throw new FullFleetException("Full fleet");
        }
    }

    @Override
    public DroneResponse loadDroneWithMedication(List<Long> medicationIds, long droneId) throws NotFoundException {
        Optional<Drones> droneToUpdate = droneRepository.findById(droneId);
        if (droneToUpdate.isPresent()) {
            List<Medication> medications = medicationService.getMedicationByIds(medicationIds);
            Drones currentDrone= droneToUpdate.get();
            currentDrone.setItems(medications);
            currentDrone= droneRepository.save(currentDrone);
            return droneResponseMapper.entityToObject(currentDrone);

        }else{
            throw new NotFoundException();
        }

    }

    @Override
    public DroneMedicationResponse checkDroneLoadMedication(long droneId) {
        Optional<Drones> droneToUpdate = droneRepository.findById(droneId);
        if (droneToUpdate.isPresent()) {
            Drones currentDrone = droneToUpdate.get();
            return DroneMedicationResponse.builder()
                    .droneId(currentDrone.getId())
                    .medications(medicationResponseMapper.entitiesToObjectList(currentDrone.getItems())).build();
        }

        return null;
    }

    @Override
    public DroneAvailableResponse getAvailableDroneForLoad(ModelEnum loadType) throws NotFoundException {
        List<Drones> availableDrones = droneRepository.findAllByStateEnumAndAndModelEnumAndBatteryCapacityGreaterThanEqual(StateEnum.IDLE,loadType,25);
        if (!availableDrones.isEmpty()){
            return DroneAvailableResponse.builder().availableDrone(droneResponseMapper.entitiesToObjectList(availableDrones)).build();
        }else{
            throw new NotFoundException();
        }
    }

    @Override
    public DroneResponse getDroneBatteryLevel(long droneId) throws NotFoundException {
        Optional<Drones> drones = droneRepository.findById(droneId);
        if(drones.isPresent()){
            return DroneResponse.builder().batteryCapacity(drones.get().getBatteryCapacity()).build();
        }else{
            throw new NotFoundException();
        }

    }
}
