package com.musala.drone.drone.service.cron;

import com.musala.drone.drone.repository.AuditRepository;
import com.musala.drone.drone.repository.DroneRepository;
import com.musala.drone.drone.repository.entity.DronEvents;
import com.musala.drone.drone.repository.entity.Drone;
import com.musala.drone.drone.repository.enums.StateEnum;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatteryCheckTask {


    private final DroneRepository droneRepository;
    private final AuditRepository auditRepository;

    public BatteryCheckTask(DroneRepository droneRepository, AuditRepository auditRepository) {
        this.droneRepository = droneRepository;
        this.auditRepository = auditRepository;
    }

    @Scheduled(fixedDelay = 5 * 60 * 1000)
    public void batteryCheck() {
        List<Drone> drones = droneRepository.findAll();
        drones.stream().forEach(drone -> {
            try {
                int batteryCapacity = drone.getBatteryCapacity();
                StateEnum state = drone.getStateEnum();
                DronEvents dronEvents = new DronEvents();
                dronEvents.setBatteryCapacity(batteryCapacity);
                dronEvents.setState(state);
                dronEvents.setDroneId(drone.getId());
                auditRepository.save(dronEvents);
            } catch (IllegalArgumentException exc) {
                throw new IllegalArgumentException("Drone not found");
            }
        });
    }
}
