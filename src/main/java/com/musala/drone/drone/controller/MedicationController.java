package com.musala.drone.drone.controller;

import com.musala.drone.drone.service.MedicationService;
import com.musala.drone.drone.service.dto.MedicationRequest;
import com.musala.drone.drone.service.dto.MedicationResponse;
import com.musala.drone.drone.util.URIConstants;
import org.apache.coyote.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = URIConstants.API_BASE_URL+URIConstants.DRONE_RESOURCE_PATH,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<MedicationResponse> registerMedication(@RequestBody MedicationRequest medicationRequest){
       return ResponseEntity.ok( medicationService.createMedication(medicationRequest));
    }


    @GetMapping(value = "/medications")
    public ResponseEntity getMedications(@RequestParam Pageable pageable){
        return ResponseEntity.ok(medicationService.getMedicationList(pageable));
    }

    @DeleteMapping(value = "/{medicationId}")
    public void deleteMedication(@PathVariable("medicationId")Long medicationId){
         medicationService.removeMedication(medicationId);
    }
}
