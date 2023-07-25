package com.example.VaccinationBookingSystem.Repositry;

import com.example.VaccinationBookingSystem.Model.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationCenterRepositry extends JpaRepository<VaccinationCenter, Integer> {
}
