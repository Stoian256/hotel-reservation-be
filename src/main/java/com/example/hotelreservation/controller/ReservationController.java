package com.example.hotelreservation.controller;

import com.example.hotelreservation.model.Reservation;
import com.example.hotelreservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/book/{roomId}")
    public Reservation bookRoom(@PathVariable Long roomId, @RequestParam String checkInTime) {
        LocalDateTime checkInDateTime = LocalDateTime.parse(checkInTime);
        return reservationService.bookRoom(roomId, checkInDateTime);
    }

    @PostMapping("/cancel/{reservationId}")
    public void cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
    }
}