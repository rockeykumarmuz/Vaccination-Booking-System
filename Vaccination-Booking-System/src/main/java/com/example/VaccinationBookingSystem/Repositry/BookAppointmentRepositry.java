package com.example.VaccinationBookingSystem.Repositry;

import com.example.VaccinationBookingSystem.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAppointmentRepositry extends JpaRepository<Appointment, Integer> {

}
