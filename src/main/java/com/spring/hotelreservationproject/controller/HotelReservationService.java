package com.spring.hotelreservationproject.controller;


import com.spring.hotelreservationproject.model.Hotel;
import com.spring.hotelreservationproject.model.HotelReservation;
import com.spring.hotelreservationproject.repository.HotelRepository;
import com.spring.hotelreservationproject.repository.HotelReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/hotel-reservation")
public class HotelReservationService {
    @Autowired
    private HotelReservationRepository hotelReservationRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewHotelReservation (@RequestBody HotelReservation hotelReservation) throws Exception {
        Hotel availableHotel = hotelRepository.findByStarAndType(hotelReservation.getHotelStar(), hotelReservation.getHotelRoomType());
        if(availableHotel != null){
            int noOfAvailableRooms = availableHotel.getHotelAvailableRooms();

            if(noOfAvailableRooms > 0){
                availableHotel.setHotelAvailableRooms(availableHotel.getHotelAvailableRooms()-1);
                availableHotel.setHotelReservedRooms(availableHotel.getHotelReservedRooms()+1);
                hotelRepository.save(availableHotel);
                hotelReservationRepository.save(hotelReservation);
            }
            else {
                throw new Exception("No available rooms!");
            }
        }
        else{
            throw new Exception("No hotel found with specified star and room type!");
        }

        return "Room reservation made!";
    }

    @PostMapping(path="/update/{id}")
    public @ResponseBody String updateHotelReservation(@RequestBody HotelReservation newHotelReservation, @PathVariable int id) throws Exception {
        HotelReservation oldHotelReservation = hotelReservationRepository.findById(id);
        Hotel oldHotel = hotelRepository.findByStarAndType(oldHotelReservation.getHotelStar(), oldHotelReservation.getHotelRoomType());
        Hotel availableHotel = hotelRepository.findByStarAndType(newHotelReservation.getHotelStar(), newHotelReservation.getHotelRoomType());
        if(availableHotel != null){
            int noOfAvailableRooms = availableHotel.getHotelAvailableRooms();
            System.out.println("noOfAvailableRooms: "+noOfAvailableRooms);
            if(noOfAvailableRooms > 0){
                availableHotel.setHotelAvailableRooms(availableHotel.getHotelAvailableRooms()-1);
                availableHotel.setHotelReservedRooms(availableHotel.getHotelReservedRooms()+1);
                hotelRepository.save(availableHotel);

                oldHotel.setHotelAvailableRooms(oldHotel.getHotelAvailableRooms()+1);
                oldHotel.setHotelReservedRooms(oldHotel.getHotelReservedRooms()-1);
                hotelRepository.save(oldHotel);

                oldHotelReservation.setHotelStar(newHotelReservation.getHotelStar());
                oldHotelReservation.setHotelRoomType(newHotelReservation.getHotelRoomType());
                hotelReservationRepository.save(oldHotelReservation);
            }
            else {
                throw new Exception("No available rooms!");
            }
        }
        else{
            throw new Exception("No hotel found with specified star and room type!");
        }
        return "Hotel reservation information successfully updated!";
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteHotelReservation(@PathVariable int id) {
        HotelReservation oldHotelReservation = hotelReservationRepository.findById(id);
        hotelReservationRepository.delete(oldHotelReservation);
        return "Hotel reservation information successfully deleted!";
    }
}
