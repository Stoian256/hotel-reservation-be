package com.example.hotelreservation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ROOM_NOT_AVAILABLE(400, "Room is not available!"),
    CANCEL_RESERVATION_NOT_AVAILABLE(400, "You can cancel reservation only 2 hours before checkIn"),
    NOT_FOUND(404, "%s not found!");

    private final Integer code;
    private final String message;
}