package com.musala.drone.drone.repository;

import com.musala.drone.drone.repository.entity.DronEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface  AuditRepository extends JpaRepository<DronEvents,Long> {
}
