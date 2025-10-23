package com.doctorbookingsystem.doctorbooking.service.impl;

import com.doctorbookingsystem.doctorbooking.dto.AvailabilitySlotDTO;
import com.doctorbookingsystem.doctorbooking.mapper.AvailabilitySlotMapper;
import com.doctorbookingsystem.doctorbooking.model.AvailabilitySlot;
import com.doctorbookingsystem.doctorbooking.model.Doctor;
import com.doctorbookingsystem.doctorbooking.repository.AvailabilitySlotRepository;
import com.doctorbookingsystem.doctorbooking.service.interfaces.AvailabilitySlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AvailabilitySlotServiceImpl implements AvailabilitySlotService {

    private final AvailabilitySlotRepository availabilitySlotRepository;
    private final AvailabilitySlotMapper slotMapper;


    public AvailabilitySlotServiceImpl(AvailabilitySlotRepository availabilitySlotRepository,AvailabilitySlotMapper slotMapper){
        this.availabilitySlotRepository=availabilitySlotRepository;
        this.slotMapper=slotMapper;

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
    public AvailabilitySlotDTO createSlot(AvailabilitySlotDTO slotDTO){

        AvailabilitySlot slot=slotMapper.toEntity(slotDTO);

        if(slot.getStart().isAfter(slot.getEnd()) || slot.getStart().equals(slot.getEnd())){
            throw new RuntimeException("start must be before end ");
        }

        if (slot.getStart().isBefore(Instant.now())) {
            throw new RuntimeException("Start time cannot be in the past.");
        }

        slot.setBooked(false);
        AvailabilitySlot savedSlot = availabilitySlotRepository.save(slot);
        return slotMapper.toDto(savedSlot);
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
    //    doctor can delete a slot
    @Override
    public AvailabilitySlot deleteSlot(String doctorId,String slotId){
        AvailabilitySlot slot=availabilitySlotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if(! slot.getDoctorId().equals(doctorId)){
            throw new RuntimeException("Doctor should delete his slot not other doctor's slot");
        }

        if(slot.isBooked()){
            throw new RuntimeException("the Slot is already booked");

        }
        AvailabilitySlot deletedSlot =slot;
        availabilitySlotRepository.delete(slot);
        return deletedSlot;
    }

}

