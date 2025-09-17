package com.doctorbookingsystem.doctorbooking.controller;

import com.doctorbookingsystem.doctorbooking.model.AvailabilitySlot;
import com.doctorbookingsystem.doctorbooking.service.interfaces.AvailabilitySlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/availability-slots")
public class AvailabilitySlotController {

    private final AvailabilitySlotService availabilitySlotService;

    public AvailabilitySlotController(AvailabilitySlotService availabilitySlotService) {
        this.availabilitySlotService = availabilitySlotService;
    }

    @PostMapping("/create")
    public ResponseEntity<AvailabilitySlot> createSlot(@RequestBody AvailabilitySlot slot) {
        log.info("Creating slot for doctorId: {}", slot.getDoctorId());
        AvailabilitySlot createdSlot = availabilitySlotService.createSlot(slot);
        return ResponseEntity.ok(createdSlot);
    }


}
