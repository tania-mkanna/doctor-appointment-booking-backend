package com.doctorbookingsystem.doctorbooking.service.interfaces;

import com.doctorbookingsystem.doctorbooking.dto.AvailabilitySlotDTO;
import com.doctorbookingsystem.doctorbooking.model.AvailabilitySlot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AvailabilitySlotService {

//    get all slot
    List<AvailabilitySlot> getAllSlots();

//    get all slots for a specific doctor
    List<AvailabilitySlot>getSlotForDoctor(String doctorId);

//    get available slots for a specific doctor (isBooked=false)
    List<AvailabilitySlot>getAvailableSlotsForDoctor(String doctorId);

//    create a slot for a doctor
AvailabilitySlotDTO createSlot(AvailabilitySlotDTO slotDTO);

//    mark a slot as booked (isBooked=true)
    AvailabilitySlot markSlotAsBooked(String slotId);

//    doctor can delete a slot
AvailabilitySlot deleteSlot(String doctorId,String slotId);


}
