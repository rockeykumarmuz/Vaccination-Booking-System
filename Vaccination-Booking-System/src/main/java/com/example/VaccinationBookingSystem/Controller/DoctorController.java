package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Dto.DoctorRequestDto;
import com.example.VaccinationBookingSystem.Dto.GetAllDoctorNameAgeGreaterThanRequestDto;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.ResponseDto.DoctoreResponseDto;
import com.example.VaccinationBookingSystem.ResponseDto.GetAllDoctorNameAgeGreaterThanResponseDto;
import com.example.VaccinationBookingSystem.Service.DoctoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctoreService doctoreService;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto) {
        try {
            DoctoreResponseDto doctoreResponseDto = doctoreService.addDoctor(doctorRequestDto);
            return new ResponseEntity(doctoreResponseDto, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_age_greater_than")
    public ResponseEntity getAgeGreaterThan(@RequestBody GetAllDoctorNameAgeGreaterThanRequestDto getAllDoctorNameAgeGreaterThanRequestDto) {

        try {
            GetAllDoctorNameAgeGreaterThanResponseDto response = doctoreService.getAgeGreaterThan(getAllDoctorNameAgeGreaterThanRequestDto);
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        } catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getDoctorsWithHighestAppointment")
    public ResponseEntity getDoctorWithHighestNumberOfAppointments() {

        List<String> doctor = doctoreService.getDoctorWithHighestNumberOfAppointments();
        return new ResponseEntity(doctor, HttpStatus.ACCEPTED);
    }

}
