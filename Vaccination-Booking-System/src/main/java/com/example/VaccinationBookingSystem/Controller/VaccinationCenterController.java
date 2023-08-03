package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Dto.VaccinationCenterRequestDto;
import com.example.VaccinationBookingSystem.Enum.CenterType;
import com.example.VaccinationBookingSystem.ResponseDto.VaccinationCenterResponseDto;
import com.example.VaccinationBookingSystem.Service.VaccinationCenterService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/center")
public class VaccinationCenterController {

    @Autowired
    VaccinationCenterService vaccinationCenterService;

    @PostMapping("/add-center")
    public ResponseEntity addCenter(@RequestBody VaccinationCenterRequestDto vaccinationCenterRequestDto) {

        VaccinationCenterResponseDto vaccinationCenterResponseDto= vaccinationCenterService.addCenter(vaccinationCenterRequestDto);
        return new ResponseEntity(vaccinationCenterResponseDto, HttpStatus.CREATED);
    }

}
