package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Dto.AddPersonRequestDto;
import com.example.VaccinationBookingSystem.Dto.GetMalesAgeGreaterThanRequestDto;
import com.example.VaccinationBookingSystem.Exception.personNotFoundException;
import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.Repositry.PersonRepositry;
import com.example.VaccinationBookingSystem.ResponseDto.AddPersonResponseDto;
import com.example.VaccinationBookingSystem.ResponseDto.GetMalesAgeGreaterThanResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepositry personRepositry;

    public AddPersonResponseDto addPerson(AddPersonRequestDto addPersonRequestDto) {

        Person person =new Person();
        person.setName(addPersonRequestDto.getName());
        person.setEmailId(addPersonRequestDto.getEmailId());
        person.setAge(addPersonRequestDto.getAge());
        person.setGender(addPersonRequestDto.getGender());
//        person.setDose1Taken(false);
//        person.setDose2Taken(false);
//        person.setCertificate(null);

        // it may throw exception for email id exist in db already to handle with try catch
        Person savedperson = personRepositry.save(person);

        // saved entity to dto conversion
        // Entity-> DTOs conversion
        AddPersonResponseDto addPersonResponseDto= new AddPersonResponseDto();
        addPersonResponseDto.setMessage("congrats you have been registered");
        addPersonResponseDto.setName(addPersonRequestDto.getName());
        return addPersonResponseDto;
    }

    public String updateEmail(String oldEmail, String newEmail) throws personNotFoundException {

        Person person = personRepositry.findByEmailId(oldEmail);

        if(person==null) {
            throw new personNotFoundException("sorry Email doesn't Exist");
        }

        person.setEmailId(newEmail);
        personRepositry.save(person);

        return "congrats! your email has been updated successfully";

    }

    public GetMalesAgeGreaterThanResponseDto getAllMalesAgeGreaterThan(GetMalesAgeGreaterThanRequestDto getMalesAgeGreaterThanRequestDto) throws personNotFoundException {

        List<Person> person = personRepositry.findByAgeGreaterThan(getMalesAgeGreaterThanRequestDto.getAge());

        if(person.size()==0) {
            throw new personNotFoundException("please enter a valid age");
        }

        List<String> al= new ArrayList<>();

        for(Person persons: person) {
            al.add(persons.getName());
        }

        GetMalesAgeGreaterThanResponseDto malesAgeGreaterThanResponseDto = new GetMalesAgeGreaterThanResponseDto();

        malesAgeGreaterThanResponseDto.setGetMales(al);

        return malesAgeGreaterThanResponseDto;
    }

    public List<String> getAllFemaleDose1Taken() {
        List<String> persons = personRepositry.findFemalesDose1TakenNotDose2();
        return persons;
    }


    public List<String> getPeopleFullyVaccinated() {
        List<String> persons = personRepositry.findAllPeopleWhoAreFullyVaccinated();
        return persons;
    }

    public List<String> getPeopleNotTakenAnyDose() {
        List<String> persons  = personRepositry.findAllPeopleWhoHaveNotTakenAnyDose();
        return persons;
    }

    public List<String> getAllMaleAgeGreaterThanFullyVaccinated() {
        List<String> persons = personRepositry.findAllMalesAgeGreaterThanAndTakenBothDose();
        return persons;
    }
}
