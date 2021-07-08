package com.spring.hotelreservationproject.controller;


import com.spring.hotelreservationproject.model.Hotel;
import com.spring.hotelreservationproject.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/hotel")
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewHotel (@RequestBody Hotel hotel) throws Exception {
        hotel.setHotelReservedRooms(0);
        hotel.setHotelAvailableRooms(hotel.getHotelTotalRooms());
        hotelRepository.save(hotel);
        return "Saved";
    }

    @PostMapping(path="/update/{id}")
    public @ResponseBody String updateHotel(@RequestBody Hotel newHotel, @PathVariable int id) throws Exception {
        Hotel oldHotel = hotelRepository.findById(id);
        oldHotel.setHotelStars(newHotel.getHotelStars());
        oldHotel.setHotelTotalRooms(newHotel.getHotelTotalRooms());
        oldHotel.setHotelRoomTypes(newHotel.getHotelRoomTypes());
        hotelRepository.save(oldHotel);
        return "Hotel information successfully updated!";
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteHotel(@PathVariable int id) {
        Hotel oldHotel = hotelRepository.findById(id);
        hotelRepository.delete(oldHotel);
        return "Hotel information successfully deleted!";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Hotel> getSummary() {
        return hotelRepository.findAll();
    }
}
