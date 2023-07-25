package com.example.VaccinationBookingSystem.Repositry;


import com.example.VaccinationBookingSystem.Model.Dose;
import com.example.VaccinationBookingSystem.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoseRepositry extends JpaRepository<Dose, Integer> {


}
