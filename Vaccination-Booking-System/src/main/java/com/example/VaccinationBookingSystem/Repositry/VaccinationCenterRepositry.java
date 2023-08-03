package com.example.VaccinationBookingSystem.Repositry;

import com.example.VaccinationBookingSystem.Enum.CenterType;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.VaccinationCenter;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationCenterRepositry extends JpaRepository<VaccinationCenter, Integer> {

}
