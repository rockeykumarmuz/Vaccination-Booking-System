package com.example.VaccinationBookingSystem.Repositry;

import com.example.VaccinationBookingSystem.Enum.DoseType;
import com.example.VaccinationBookingSystem.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepositry extends JpaRepository<Person, Integer> {

    Person findByEmailId(String newEmail);

    List<Person> findByAge(int age);

    List<Person> findByAgeAndName(int age,String name);

    @Query(value = "select * from person where age > :age", nativeQuery = true)
    List<Person> findByAgeGreaterThan(int age);

    // not optimized
    @Query(value = "select name from person where dose1taken=true and dose2taken=false and gender='FEMALE' ", nativeQuery = true)
    List<String> findFemalesDose1TakenNotDose2();

    @Query(value = "select name from person where dose1taken=true and dose2taken=true", nativeQuery = true)
    List<String> findAllPeopleWhoAreFullyVaccinated();

    @Query(value = "select name from person where dose1taken=false and dose2taken=false", nativeQuery = true)
    List<String> findAllPeopleWhoHaveNotTakenAnyDose();

    // not optimized
    @Query(value = "select name from person where dose1taken=true and dose2taken=true and gender=\"MALE\" and age>22", nativeQuery = true)
    List<String> findAllMalesAgeGreaterThanAndTakenBothDose();

//    @Query(value = "select doseType from dose where person.Id = personId", nativeQuery = true)
//    public DoseType getDoseType(int personId);

}
