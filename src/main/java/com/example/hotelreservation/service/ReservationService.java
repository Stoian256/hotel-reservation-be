package com.example.hotelreservation.service;

import com.example.hotelreservation.exception.EntityValidationException;
import com.example.hotelreservation.exception.ErrorCode;
import com.example.hotelreservation.model.Reservation;
import com.example.hotelreservation.model.Room;
import com.example.hotelreservation.repository.ReservationRepository;
import com.example.hotelreservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final RoomRepository roomRepository;

    public Reservation bookRoom(Long roomId, LocalDateTime checkInTime) {
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new EntityValidationException(ErrorCode.NOT_FOUND, "Room"));

        if (!room.isAvailable())
            throw new EntityValidationException(ErrorCode.ROOM_NOT_AVAILABLE);


        room.setAvailable(false);
        roomRepository.save(room);

        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setCheckInTime(checkInTime);
        reservation.setReservationTime(LocalDateTime.now());
        return reservationRepository.save(reservation);

    }

    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository
                .findById(reservationId)
                .orElseThrow(() -> new EntityValidationException(ErrorCode.NOT_FOUND, "Reservation"));

        if (reservation.getCheckInTime().isBefore(LocalDateTime.now().plusHours(2)))
            throw new EntityValidationException(ErrorCode.CANCEL_RESERVATION_NOT_AVAILABLE);

        Room room = reservation.getRoom();
        room.setAvailable(true);
        roomRepository.save(room);
        reservationRepository.delete(reservation);
    }
}

