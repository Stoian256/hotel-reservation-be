package com.example.hotelreservation.controller;

import com.example.hotelreservation.model.Hotel;
import com.example.hotelreservation.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/nearby")
    public List<Hotel> findNearbyHotels(@RequestParam double lat, @RequestParam double lon, @RequestParam double radius) {
        return hotelService.findNearbyHotels(lat, lon, radius);
    }

    @GetMapping("/{id}")
    public Hotel getHotel(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }
}

