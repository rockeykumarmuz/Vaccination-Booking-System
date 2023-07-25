package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Dto.BookDose1RequestDto;
import com.example.VaccinationBookingSystem.Enum.DoseType;
import com.example.VaccinationBookingSystem.Exception.Dose2VaccineTypeMustBeSameAsDoseType1VaccineException;
import com.example.VaccinationBookingSystem.Exception.DoseAlreadyTakenException;
import com.example.VaccinationBookingSystem.Exception.personNotFoundException;
import com.example.VaccinationBookingSystem.Model.Dose;
import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.Repositry.DoseRepositry;
import com.example.VaccinationBookingSystem.Repositry.PersonRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {

    @Autowired
    DoseRepositry doseRepositry;

    @Autowired
    PersonRepositry personRepositry;

//    public Dose addDose1(int personId, DoseType doseType) throws personNotFoundException, DoseAlreadyTakenException {
//
//        Optional<Person> optionalPerson = personRepositry.findById(personId);
//
//        // if dose not exist then throw exception
//        if(!optionalPerson.isPresent()) {
//            throw new personNotFoundException("Invalid personId");
//        }
//
//        // getting actual person
//        Person person= optionalPerson.get();
//
//        if(person.isDose1Taken()) {
//            throw new DoseAlreadyTakenException("Dose 1 Already Taken");
//        }
//
//        Dose dose = new Dose();
//
//        // dose id setting
//        dose.setDoseId(String.valueOf(UUID.randomUUID()));
//        dose.setDoseType(doseType);
//        dose.setPerson(person);
//
//        //setting person taken dose1
//        person.setDose1Taken(true);
//        // getting arraylist of dose and setting it value
//        person.getDoseTaken().add(dose);
//        personRepositry.save(person);
//
//        return doseRepositry.save(dose);
//
//    }

    public Dose addDose1(BookDose1RequestDto bookDose1RequestDto) throws personNotFoundException, DoseAlreadyTakenException {

        Optional<Person> optionalPerson = personRepositry.findById(bookDose1RequestDto.getPersonId());

        // if dose not exist then throw exception
        if(!optionalPerson.isPresent()) {
            throw new personNotFoundException("Invalid personId");
        }

        // getting actual person
        Person person= optionalPerson.get();

        if(person.isDose1Taken()) {
            throw new DoseAlreadyTakenException("Dose 1 Already Taken");
        }

        Dose dose = new Dose();

        // dose id setting
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(bookDose1RequestDto.getDoseType());
        dose.setPerson(person);

        //setting person taken dose1
        person.setDose1Taken(true);
        // getting arraylist of dose and setting it value
        person.getDoseTaken().add(dose);
        personRepositry.save(person);

        return doseRepositry.save(dose);

    }

//    public Dose addDose2(int personId, DoseType doseType) throws personNotFoundException, DoseAlreadyTakenException, Dose2VaccineTypeMustBeSameAsDoseType1VaccineException {
//
//        Optional<Person> optionalPerson = personRepositry.findById(personId);
//
//        if(!optionalPerson.isPresent()) {
//            throw new personNotFoundException("Invalid PersonId");
//        }
//
//        Person person = optionalPerson.get();
//
//        if(person.isDose2Taken()) {
//            throw new DoseAlreadyTakenException("Dose 2 already taken");
//        }
//
//        Dose dose = new Dose();
//
//        // checking that dose 2 type must be same as dose 1 type
////        String dose1 = dose.getDoseType() +"";
////        String dose2 = doseType + "";
////
////        if(!dose1.equals(dose2)) {
////            throw new Dose2VaccineTypeMustBeSameAsDoseType1VaccineException("Dose 2 vaccine type must be same as Dose 1 vaccine type");
////        }
//
//        dose.setDoseId(String.valueOf(UUID.randomUUID()));
//        dose.setDoseType(doseType);
//        person.setDose2Taken(true);
//        dose.setPerson(person);
//
//
//        personRepositry.save(person);
//
//        return doseRepositry.save(dose);
//
//    }
}
