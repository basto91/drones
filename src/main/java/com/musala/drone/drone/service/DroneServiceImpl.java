package com.musala.drone.drone.service;

import com.musala.drone.drone.repository.DroneRepository;
import com.musala.drone.drone.repository.entity.Drone;
import com.musala.drone.drone.repository.entity.Medication;
import com.musala.drone.drone.repository.enums.ModelEnum;
import com.musala.drone.drone.repository.enums.StateEnum;
import com.musala.drone.drone.service.dto.*;
import com.musala.drone.drone.service.mapper.DroneRequestMapper;
import com.musala.drone.drone.service.mapper.DroneResponseMapper;
import com.musala.drone.drone.service.mapper.MedicationResponseMapper;
import com.musala.drone.drone.util.exceptions.FullFleetException;
import com.musala.drone.drone.util.exceptions.LowBatteryException;
import com.musala.drone.drone.util.exceptions.NotFoundException;
import com.musala.drone.drone.util.exceptions.ToHeavyLoadException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            Drone drone =droneRequestMapper.objectToEntity(droneRequest);
            Drone savedDrone = droneRepository.save(drone);
            return droneResponseMapper.entityToObject(savedDrone);
        }else{

             throw new FullFleetException("Full fleet");
        }
    }

    @Override
    public DroneResponse loadDroneWithMedication(List<Long> medicationIds, long droneId) throws NotFoundException {
        Optional<Drone> droneToUpdate = droneRepository.findById(droneId);
        if (droneToUpdate.isPresent()) {
            if(droneToUpdate.get().getBatteryCapacity()>=25) {
                List<Medication> medications = medicationService.getMedicationByIds(medicationIds);
                if (isLoadToHeavy(medications,droneToUpdate.get().getWeight())){
                    throw new ToHeavyLoadException();
                }
                Drone currentDrone = droneToUpdate.get();
                currentDrone.setItems(medications);
                currentDrone = droneRepository.save(currentDrone);
                return droneResponseMapper.entityToObject(currentDrone);
            }else {
                throw new LowBatteryException();
            }

        }else{
            throw new NotFoundException("Drone not found");
        }

    }

    @Override
    public DroneMedicationResponse checkDroneLoadMedication(long droneId) {
        Optional<Drone> droneToUpdate = droneRepository.findById(droneId);
        if (droneToUpdate.isPresent()) {
            Drone currentDrone = droneToUpdate.get();
            return DroneMedicationResponse.builder()
                    .droneId(currentDrone.getId())
                    .medications(medicationResponseMapper.entitiesToObjectList(currentDrone.getItems())).build();
        }

        return null;
    }

    @Override
    public DroneAvailableResponse getAvailableDroneForLoad(ModelEnum loadType) throws NotFoundException {
        List<Drone> availableDrones = droneRepository.findAllByStateEnumAndAndModelEnumAndBatteryCapacityGreaterThanEqual(StateEnum.IDLE,loadType,25);
        if (!availableDrones.isEmpty()){
            return DroneAvailableResponse.builder().availableDrone(droneResponseMapper.entitiesToObjectList(availableDrones)).build();
        }else{
            throw new NotFoundException("Not available drones found");
        }
    }

    @Override
    public DroneResponse getDroneBatteryLevel(long droneId) throws NotFoundException {
        Optional<Drone> drones = droneRepository.findById(droneId);
        if(drones.isPresent()){
            return DroneResponse.builder().batteryCapacity(drones.get().getBatteryCapacity()).id(drones.get().getId()).build();
        }else{
            throw new NotFoundException("Drone not found");
        }

    }

    @Override
    public Page getPageableDrone(Pageable page) {
        return droneRepository.findAll(page);
    }

    private boolean isLoadToHeavy(List<Medication> medications,long droneLimitWeight){
        long weightSum=medications.stream().map(Medication::getWeight).reduce(0L,Long::sum);
        return weightSum>droneLimitWeight;
    }
}
