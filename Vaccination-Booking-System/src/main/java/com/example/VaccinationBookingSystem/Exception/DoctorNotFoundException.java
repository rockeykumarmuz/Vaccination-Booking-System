package com.example.VaccinationBookingSystem.Exception;

public class DoctorNotFoundException extends RuntimeException{

    public DoctorNotFoundException(String message) {
        super(message);
    }
}
