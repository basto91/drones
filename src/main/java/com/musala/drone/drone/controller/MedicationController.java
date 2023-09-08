package com.musala.drone.drone.controller;

import com.musala.drone.drone.service.MedicationService;
import com.musala.drone.drone.service.dto.MedicationRequest;
import com.musala.drone.drone.service.dto.MedicationResponse;
import com.musala.drone.drone.util.URIConstants;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.util.Objects;

@RestController
@RequestMapping(value = URIConstants.API_BASE_URL+URIConstants.MEDICATION_RESOURCE_PATH)
@Validated
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PostMapping(value = "/register",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MedicationResponse> registerMedication(@RequestParam String name,@RequestParam long weight,@RequestParam String code ,@RequestParam("file") MultipartFile file){

       MedicationRequest medicationRequest = MedicationRequest.builder().name(name).weight(weight).code(code).build();
        if (Objects.nonNull(file)) {
            medicationRequest.setImage(file);
        }
       return ResponseEntity.ok( medicationService.createMedication(medicationRequest));
    }


    @GetMapping(value = "/medications")
    public ResponseEntity getMedications( Pageable pageable){
        return ResponseEntity.ok(medicationService.getMedicationList(pageable));
    }

    @DeleteMapping(value = "/{medicationId}")
    public void deleteMedication(@PathVariable("medicationId")Long medicationId){
         medicationService.removeMedication(medicationId);
    }
}
