package com.example.hotelreservation.service;

import com.example.hotelreservation.exception.EntityValidationException;
import com.example.hotelreservation.exception.ErrorCode;
import com.example.hotelreservation.model.Hotel;
import com.example.hotelreservation.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public List<Hotel> findNearbyHotels(double userLat, double userLong, double radius) {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream()
                .filter(hotel -> calculateDistanceHaversineFomrula(userLat, userLong, hotel.getLatitude(), hotel.getLongitude()) <= radius)
                .toList();
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository
                .findById(id)
                .orElseThrow(() -> new EntityValidationException(ErrorCode.NOT_FOUND, "Hotel"));
    }

    //https://www.baeldung.com/java-find-distance-between-points
    //I used this formula because it improves the accuracy of the calculation
    private double calculateDistanceHaversineFomrula(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the Earth in kilometers
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}