package com.example.hotelreservation.utils;

import com.example.hotelreservation.model.Hotel;
import com.example.hotelreservation.repository.HotelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//https://www.danvega.dev/blog/read-json-data-spring-boot-write-database
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final HotelRepository hotelRepository;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Hotel>> typeReference = new TypeReference<>(){};
        InputStream inputStream = new ClassPathResource("hotels.json").getInputStream();
        try {
            List<Hotel> hotels = mapper.readValue(inputStream, typeReference);
            hotelRepository.saveAll(hotels);
            System.out.println("Hotels data loaded successfully!");
        } catch (IOException e) {
            System.out.println("Unable to load hotels data: " + e.getMessage());
        }
    }
}
