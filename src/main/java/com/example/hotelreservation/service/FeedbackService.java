package com.example.hotelreservation.service;

import com.example.hotelreservation.exception.EntityValidationException;
import com.example.hotelreservation.exception.ErrorCode;
import com.example.hotelreservation.model.Feedback;
import com.example.hotelreservation.model.Hotel;
import com.example.hotelreservation.repository.FeedbackRepository;
import com.example.hotelreservation.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    private final HotelRepository hotelRepository;

    public Feedback leaveFeedback(Long hotelId, String comments, int cleanlinessRating, int serviceRating) {
        Feedback feedback = new Feedback();

        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new EntityValidationException(ErrorCode.NOT_FOUND, "Hotel"));

        feedback.setHotel(hotel);
        feedback.setComments(comments);
        feedback.setCleanlinessRating(cleanlinessRating);
        feedback.setServiceRating(serviceRating);

        System.out.println(feedback);
        return feedbackRepository.save(feedback);
    }
}

