package com.spring.hotelreservationproject.repository;

import com.spring.hotelreservationproject.model.HotelReservation;
import org.springframework.data.repository.CrudRepository;

public interface HotelReservationRepository extends CrudRepository<HotelReservation, Integer> {
    HotelReservation findById(int id);
}
