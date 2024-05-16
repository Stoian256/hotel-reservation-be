package com.example.hotelreservation.controller;

import com.example.hotelreservation.model.Feedback;
import com.example.hotelreservation.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("/{hotelId}")
    public Feedback leaveFeedback(@PathVariable Long hotelId, @RequestParam String comments, @RequestParam int cleanlinessRating, @RequestParam int serviceRating) {
        return feedbackService.leaveFeedback(hotelId, comments, cleanlinessRating, serviceRating);
    }
}
