package com.musala.drone.drone.repository;

import com.musala.drone.drone.repository.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication,Long> {

    List<Medication> findAllByIdIn(List<Long> medicationsId);
}
