package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Dto.BookDose1RequestDto;
import com.example.VaccinationBookingSystem.Dto.BookDose2RequestDto;
import com.example.VaccinationBookingSystem.Enum.DoseType;
import com.example.VaccinationBookingSystem.Model.Dose;
import com.example.VaccinationBookingSystem.ResponseDto.BookDose1ResponseDto;
import com.example.VaccinationBookingSystem.ResponseDto.BookDose2ResponseDto;
import com.example.VaccinationBookingSystem.Service.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseService doseService;

//    @PostMapping("/add_dose_1")
//    public ResponseEntity addDose1(@RequestParam("id") int pesronId, @RequestParam("doseType")DoseType doseType) {
//
//        try
//        {
//           // int a=2/0;
//            Dose dose = doseService.addDose1(pesronId, doseType);
//            return new ResponseEntity(dose, HttpStatus.CREATED);
//        }
//        catch(Exception e) {
//            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//
//    }

    @PostMapping("/add_dose_1")
    public ResponseEntity addDose1(@RequestBody BookDose1RequestDto bookDose1RequestDto) {

        try
        {
            // int a=2/0;
            BookDose1ResponseDto responseDto = doseService.addDose1(bookDose1RequestDto);
            return new ResponseEntity(responseDto, HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/add_dose_2")
    public ResponseEntity addDose2(@RequestBody BookDose2RequestDto bookDose2RequestDto) {

        try
        {
            BookDose2ResponseDto responseDto = doseService.addDose2(bookDose2RequestDto);
            return new ResponseEntity(responseDto, HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
