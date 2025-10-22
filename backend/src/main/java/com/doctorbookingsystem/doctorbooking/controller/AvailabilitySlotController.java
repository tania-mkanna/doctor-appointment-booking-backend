package com.doctorbookingsystem.doctorbooking.controller;

import com.doctorbookingsystem.doctorbooking.dto.AvailabilitySlotDTO;
import com.doctorbookingsystem.doctorbooking.model.AvailabilitySlot;
import com.doctorbookingsystem.doctorbooking.service.interfaces.AvailabilitySlotService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
    @RequestMapping("/api/availability-slots")
public class AvailabilitySlotController {

    private final AvailabilitySlotService availabilitySlotService;

    public AvailabilitySlotController(AvailabilitySlotService availabilitySlotService) {
        this.availabilitySlotService = availabilitySlotService;
    }

    //doctor create a slot
    @PostMapping("/doctor/{doctorId}/create")
    public ResponseEntity<AvailabilitySlot> createSlot(@RequestBody AvailabilitySlotDTO slotDTO) {
        log.info("Creating slot for doctorId: {}", slotDTO.getDoctorId());
        AvailabilitySlot createdSlot = availabilitySlotService.createSlot(slotDTO);
        return ResponseEntity.ok(createdSlot);
    }

//    doctor view his slots
    @GetMapping("/doctor/{doctorId}/all")
    public List<AvailabilitySlot> getSlotForDoctor(@PathVariable String doctorId){
        log.info("get slots for doctor");
        return availabilitySlotService.getSlotForDoctor(doctorId);
    }

//    doctor view his available slots(isBooked=true)
    @GetMapping("/doctor/{doctorId}/available")
    public List<AvailabilitySlot>getAvailableSlotsForDoctor(@PathVariable String doctorId){
        log.info("get available slot for a specific doctor");
        return availabilitySlotService.getAvailableSlotsForDoctor(doctorId);
    }

//    doctor delete a slot
    @DeleteMapping("/doctor/{doctorId}/slot/{slotId}")
    public ResponseEntity<AvailabilitySlot> deleteSlot(@PathVariable("doctorId")  String doctorId, @PathVariable("slotId") String slotId){

        log.info("doctorId :{} delete a slotId :{}",doctorId,slotId);
        return ResponseEntity.ok(availabilitySlotService.deleteSlot(doctorId,slotId));
    }


}
