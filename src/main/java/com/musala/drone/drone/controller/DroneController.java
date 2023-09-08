package com.musala.drone.drone.controller;

import com.musala.drone.drone.repository.enums.ModelEnum;
import com.musala.drone.drone.service.DroneService;
import com.musala.drone.drone.service.dto.*;
import com.musala.drone.drone.util.URIConstants;
import com.musala.drone.drone.util.exceptions.FullFleetException;
import com.musala.drone.drone.util.exceptions.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = URIConstants.API_BASE_URL+URIConstants.DRONE_RESOURCE_PATH,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping(value = "/register" )
    public ResponseEntity<DroneResponse> registerDrone(@RequestBody DroneRequest droneRequest) throws FullFleetException {
       return ResponseEntity.ok(droneService.registerDrone(droneRequest));
    }

    @PatchMapping(value = "/{droneId}/load")
    public ResponseEntity<DroneResponse> loadDroneWithMedication(@RequestParam("medicationIds") List<Long> medications,@PathVariable("droneId") Long droneId) throws NotFoundException {
        return ResponseEntity.ok(droneService.loadDroneWithMedication(medications,droneId));
    }

    @GetMapping(value="/{droneId}/medication")
    public ResponseEntity<DroneMedicationResponse>checkDroneLoadMedication(@PathVariable("droneId")long droneId){
        return ResponseEntity.ok(droneService.checkDroneLoadMedication(droneId));
    }

    @GetMapping(value="/available/{loadType}")
    public ResponseEntity<DroneAvailableResponse>getAvailableDrone(@PathVariable("loadType")ModelEnum loadType) throws NotFoundException {
        return ResponseEntity.ok(droneService.getAvailableDroneForLoad(loadType));
    }
    @GetMapping(value="/drones")
    public ResponseEntity getPageDrones(Pageable pageable) throws NotFoundException {
        return ResponseEntity.ok(droneService.getPageableDrone(pageable));
    }
    @GetMapping(value = "/{droneId}/battery")
    public ResponseEntity<DroneResponse>getDroneBatteryLevel(@PathVariable("droneId")Long droneId) throws NotFoundException {
        return ResponseEntity.ok(droneService.getDroneBatteryLevel(droneId));
    }
}
