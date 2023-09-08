package com.musala.drone.drone.service;

import com.musala.drone.drone.repository.enums.ModelEnum;
import com.musala.drone.drone.service.dto.*;
import com.musala.drone.drone.util.exceptions.FullFleetException;
import com.musala.drone.drone.util.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DroneService {

    DroneResponse registerDrone(DroneRequest droneRequest) throws FullFleetException;
    DroneResponse loadDroneWithMedication(List<Long> medicationIds, long droneId) throws NotFoundException;

    DroneMedicationResponse checkDroneLoadMedication(long droneId);

    DroneAvailableResponse getAvailableDroneForLoad(ModelEnum loadType) throws NotFoundException;

    DroneResponse getDroneBatteryLevel(long doneId) throws NotFoundException;

    Page getPageableDrone(Pageable page);
}
