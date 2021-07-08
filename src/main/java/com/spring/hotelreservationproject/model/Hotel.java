package com.spring.hotelreservationproject.model;

import javax.persistence.*;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String hotelStars;

    private int hotelTotalRooms;

    private int hotelReservedRooms;

    private int hotelAvailableRooms;

    private String hotelRoomTypes;

    protected Hotel() {}

    public Hotel(String hotelStars, int hotelTotalRooms, String hotelRoomTypes) throws Exception {
        setHotelStars(hotelStars);
        setHotelTotalRooms(hotelTotalRooms);
        setHotelRoomTypes(hotelRoomTypes);
    }

    public Integer getId() {
        return id;
    }

    public String getHotelStars() {
        return hotelStars;
    }

    public void setHotelStars(String hotelStars) throws Exception {
        if(Integer.parseInt(hotelStars) >= 1 && Integer.parseInt(hotelStars) <= 5){
            this.hotelStars = hotelStars;
        }
        else{
            throw new Exception("Hotel star value not allowed");
        }
    }

    public int getHotelTotalRooms() {
        return hotelTotalRooms;
    }

    public void setHotelTotalRooms(int hotelTotalRooms) {
        this.hotelTotalRooms = hotelTotalRooms;
    }

    public String getHotelRoomTypes() {
        return hotelRoomTypes;
    }

    public void setHotelRoomTypes(String hotelRoomTypes) throws Exception {
        if(hotelRoomTypes.equals("single") || hotelRoomTypes.equals("double")){
            this.hotelRoomTypes = hotelRoomTypes;
        }
        else{
            throw new Exception("Hotel room type value not allowed");
        }
    }

    public int getHotelReservedRooms() {
        return hotelReservedRooms;
    }

    public void setHotelReservedRooms(int hotelReservedRooms) {
        this.hotelReservedRooms = hotelReservedRooms;
    }

    public int getHotelAvailableRooms() {
        return hotelAvailableRooms;
    }

    public void setHotelAvailableRooms(int hotelAvailableRooms) {
        this.hotelAvailableRooms = hotelAvailableRooms;
    }
}
