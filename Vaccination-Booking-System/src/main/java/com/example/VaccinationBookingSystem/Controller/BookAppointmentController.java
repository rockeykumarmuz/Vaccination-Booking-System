package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.ResponseDto.BookAppointmentRequestDto;
import com.example.VaccinationBookingSystem.ResponseDto.BookAppointmentResponseDto;
import com.example.VaccinationBookingSystem.Service.BookAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class BookAppointmentController {

    @Autowired
    BookAppointmentService bookAppointmentService;


    @PostMapping("/book")
    public ResponseEntity bookAppointment(@RequestBody BookAppointmentRequestDto bookAppointmentRequestDto) {

        try {
            BookAppointmentResponseDto response = bookAppointmentService.bookAppointment(bookAppointmentRequestDto);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
            catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
            }
    }
}
