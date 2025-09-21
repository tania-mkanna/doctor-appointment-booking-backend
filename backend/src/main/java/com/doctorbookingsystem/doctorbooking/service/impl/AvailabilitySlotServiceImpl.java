package com.doctorbookingsystem.doctorbooking.service.impl;

import com.doctorbookingsystem.doctorbooking.model.AvailabilitySlot;
import com.doctorbookingsystem.doctorbooking.repository.AvailabilitySlotRepository;
import com.doctorbookingsystem.doctorbooking.service.interfaces.AvailabilitySlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AvailabilitySlotServiceImpl implements AvailabilitySlotService {

    private final AvailabilitySlotRepository availabilitySlotRepository;


    public AvailabilitySlotServiceImpl(AvailabilitySlotRepository availabilitySlotRepository){
        this.availabilitySlotRepository=availabilitySlotRepository;

    }

//get all slots
    @Override
    public List<AvailabilitySlot> getAllSlots(){
        log.info("Fetching all slots");
        return availabilitySlotRepository.findAll();
    }
//get all slots for a specific doctor
    @Override
    public List<AvailabilitySlot>getSlotForDoctor(String doctorId){
        log.info("get all slots for a specific doctor");
        return availabilitySlotRepository.findByDoctorId(doctorId);
    }

//    get available slots for a specific doctor (isBooked=false)
    @Override
    public  List<AvailabilitySlot>getAvailableSlotsForDoctor(String doctorId){
        log.info(" get available slots for a specific doctor (isBooked=false)");
        return availabilitySlotRepository.findByDoctorIdAndIsBookedFalse(doctorId);
    }
//create a slot
    @Override
    public AvailabilitySlot createSlot(AvailabilitySlot slot,String doctorId){
        log.info("Doctor {} create a slot from {} to {}",doctorId,slot.getStart(),slot.getEnd());

        if(slot.getStart().isAfter(slot.getEnd()) || slot.getStart().equals(slot.getEnd())){
            throw new RuntimeException("start must be before end ");
        }

        slot.setDoctorId(doctorId);
        slot.setBooked(false);
        return availabilitySlotRepository.save(slot);
    }

//    mark isBooked=true
    @Override
    public AvailabilitySlot markSlotAsBooked(String slotId){

        AvailabilitySlot slot = availabilitySlotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (slot.isBooked()) {
            throw new RuntimeException("Slot is already booked!");
        }
        slot.setBooked(true);
        return availabilitySlotRepository.save(slot);

    }
}

