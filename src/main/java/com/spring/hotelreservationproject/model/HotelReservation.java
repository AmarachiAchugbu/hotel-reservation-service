package com.spring.hotelreservationproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HotelReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String hotelStar;

    private String hotelRoomType;

    protected HotelReservation() {}

    public HotelReservation(String hotelStar, String hotelRoomType) throws Exception {
        setHotelStar(hotelStar);
        setHotelRoomType(hotelRoomType);
    }

    public Integer getId() {
        return id;
    }

    public String getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(String hotelStar) throws Exception {
        if(Integer.parseInt(hotelStar) >= 1 && Integer.parseInt(hotelStar) <= 5){
            this.hotelStar = hotelStar;
        }
        else{
            throw new Exception("Hotel star value not allowed");
        }
    }

    public String getHotelRoomType() {
        return hotelRoomType;
    }

    public void setHotelRoomType(String hotelRoomType) throws Exception {
        if(hotelRoomType.equals("single") || hotelRoomType.equals("double")){
            this.hotelRoomType = hotelRoomType;
        }
        else{
            throw new Exception("Hotel room type value not allowed");
        }
    }
}
