package com.spring.hotelreservationproject.repository;

import com.spring.hotelreservationproject.model.Hotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, Integer> {
    Hotel findById(int id);

    @Query("select h from Hotel h where h.hotelStars like %?1 and  h.hotelRoomTypes like %?2")
    Hotel findByStarAndType(String hotelStars, String hotelRoomTypes);
}
