package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Dto.AddPersonRequestDto;
import com.example.VaccinationBookingSystem.Dto.GetMalesAgeGreaterThanRequestDto;
import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.ResponseDto.AddPersonResponseDto;
import com.example.VaccinationBookingSystem.ResponseDto.GetMalesAgeGreaterThanResponseDto;
import com.example.VaccinationBookingSystem.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("/add")
    public ResponseEntity addPerson(@RequestBody AddPersonRequestDto addPersonRequestDto) {

        try {
            AddPersonResponseDto addPersonResponseDto = personService.addPerson(addPersonRequestDto);
            return new ResponseEntity(addPersonResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Invalid Request", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateEmail")
    public ResponseEntity updateEmail(@RequestParam("oldEmail") String oldEmail, @RequestParam("newEmail") String newEmail) {
        try {
            String ans = personService.updateEmail(oldEmail, newEmail);
            return new ResponseEntity(ans, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllMalesAgeGreaterThan")
    public ResponseEntity getAllMalesAgeGreaterThan(@RequestBody GetMalesAgeGreaterThanRequestDto getMalesAgeGreaterThanRequestDto) {
        try {
            GetMalesAgeGreaterThanResponseDto response = personService.getAllMalesAgeGreaterThan(getMalesAgeGreaterThanRequestDto);
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllFemalesTakenDose1")
    public ResponseEntity getAllFemaleDose1Taken() {
        List<String> ans = personService.getAllFemaleDose1Taken();
        return new ResponseEntity(ans, HttpStatus.FOUND);
    }

    @GetMapping("/getPeopleFullyVaccinated")
    public ResponseEntity getPeopleFullyVaccinated() {
        List<String> ans = personService.getPeopleFullyVaccinated();
        return new ResponseEntity(ans, HttpStatus.FOUND);
    }

    @GetMapping("/getPeopleWhoNotTakenDose")
    public ResponseEntity getPeopleNotTakenAnyDose() {
        List<String> ans = personService.getPeopleNotTakenAnyDose();
        return new ResponseEntity(ans, HttpStatus.FOUND);
    }

    @GetMapping("/getMalesAgeGreaterThanAndVaccinated")
    public ResponseEntity getAllMaleAgeGreaterThanFullyVaccinated() {
        List<String> ans  = personService.getAllMaleAgeGreaterThanFullyVaccinated();
        return new ResponseEntity(ans, HttpStatus.FOUND);
    }

}
