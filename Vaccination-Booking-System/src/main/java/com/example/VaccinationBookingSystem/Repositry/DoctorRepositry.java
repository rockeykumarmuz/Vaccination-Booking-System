package com.example.VaccinationBookingSystem.Repositry;

import com.example.VaccinationBookingSystem.Enum.CenterType;
import com.example.VaccinationBookingSystem.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepositry extends JpaRepository<Doctor, Integer> {

    @Query(value = "select * from doctor where age > :age", nativeQuery = true)
    List<Doctor> findByAgeGreaterThan(int age);


//    @Query(value = "select * from vaccinationCenter where centerType = CenterType.centerType ", nativeQuery = true)
//    public List<Doctor> getAllDoctorOfParticularCenterType(CenterType centerType);

}
