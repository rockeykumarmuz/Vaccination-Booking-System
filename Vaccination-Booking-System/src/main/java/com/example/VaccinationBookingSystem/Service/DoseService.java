package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Dto.BookDose1RequestDto;
import com.example.VaccinationBookingSystem.Dto.BookDose2RequestDto;
import com.example.VaccinationBookingSystem.Enum.DoseType;
import com.example.VaccinationBookingSystem.Exception.Dose1NotTakenException;
import com.example.VaccinationBookingSystem.Exception.Dose2VaccineTypeMustBeSameAsDoseType1VaccineException;
import com.example.VaccinationBookingSystem.Exception.DoseAlreadyTakenException;
import com.example.VaccinationBookingSystem.Exception.personNotFoundException;
import com.example.VaccinationBookingSystem.Model.Certificate;
import com.example.VaccinationBookingSystem.Model.Dose;
import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.Repositry.DoseRepositry;
import com.example.VaccinationBookingSystem.Repositry.PersonRepositry;
import com.example.VaccinationBookingSystem.ResponseDto.BookDose1ResponseDto;
import com.example.VaccinationBookingSystem.ResponseDto.BookDose2ResponseDto;
import org.apache.catalina.LifecycleState;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {

    @Autowired
    DoseRepositry doseRepositry;

    @Autowired
    PersonRepositry personRepositry;

    @Autowired
    JavaMailSender javaMailSender;


    public BookDose1ResponseDto addDose1(BookDose1RequestDto bookDose1RequestDto) throws personNotFoundException, DoseAlreadyTakenException {

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
        Person savedPerson = personRepositry.save(person);

        Certificate certificate = new Certificate();
        certificate.setCertificateNo(String.valueOf(UUID.randomUUID()));
        certificate.setConfirmationMessage("Certificate! generated for dose 1.");

        // sending mail to the person who have done their dose 1
        String text = "Congratulation!" + person.getName() + "you have done you dose 1 vaccination of type "+bookDose1RequestDto.getDoseType()+ " with dose Id " + savedPerson.getId() + " please keep this auto generated certificate for proof or authenticity with certificate no " + certificate.getCertificateNo();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("rockeykumarsoftengg@gmail.com");
        simpleMailMessage.setSubject("Dose 1 vaccination done.");
        simpleMailMessage.setTo(person.getEmailId());
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

        BookDose1ResponseDto bookDose1ResponseDto = new BookDose1ResponseDto();
        bookDose1ResponseDto.setPersonName(person.getName());
        bookDose1ResponseDto.setCertificateNo(certificate.getCertificateNo());
        bookDose1ResponseDto.setCertificateMsg(certificate.getConfirmationMessage());
        bookDose1ResponseDto.setMessage("Congratulation! "+person.getName() + " you have taken dose 1 vaccine of type "+bookDose1RequestDto.getDoseType());

        return bookDose1ResponseDto;

    }

    public BookDose2ResponseDto addDose2(BookDose2RequestDto bookDose2RequestDto) throws personNotFoundException, DoseAlreadyTakenException, Dose2VaccineTypeMustBeSameAsDoseType1VaccineException {

        Person person = personRepositry.findByEmailId(bookDose2RequestDto.getPersonEmail());

        if(person==null) {
            throw new personNotFoundException("Invalid PersonId");
        }

        if(!person.isDose1Taken()) {
            throw new Dose1NotTakenException("Sorry! you have not taken dose1 Please, ensure to take Dose1 first then Dose2");
        }

        if(person.isDose2Taken()) {
            throw new DoseAlreadyTakenException("Dose 2 already taken");
        }

        Dose dose = new Dose();

        // getting dose1 type so that dose2 can be provided with the same type
        List<Dose> doseList= person.getDoseTaken(); // get list of dose taken for person and get latest dose and their type
        Dose latestDose = doseList.get(doseList.size()-1);
        DoseType doseType = latestDose.getDoseType();

        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        person.setDose2Taken(true);
        dose.setDoseType(doseType);
        person.getDoseTaken().add(dose);
        dose.setPerson(person);

        Person savedPerson = personRepositry.save(person);

        Certificate certificate = new Certificate();
        certificate.setCertificateNo(String.valueOf(UUID.randomUUID()));
        certificate.setConfirmationMessage("Certificate! generated for dose 2.");

        // sending mail to the person who have done their dose 1
        String text = "Congratulation!" + person.getName() + "you have fully vaccinated with dose type "+doseType+ " with dose Id " + savedPerson.getId() + " please keep this auto generated certificate for proof or authenticity with certificate no " + certificate.getCertificateNo();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("rockeykumarsoftengg@gmail.com");
        simpleMailMessage.setSubject("Dose 2 vaccination done and you are fully vaccinated!.");
        simpleMailMessage.setTo(person.getEmailId());
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

        BookDose2ResponseDto bookDose2ResponseDto = new BookDose2ResponseDto();
        bookDose2ResponseDto.setPersonName(person.getName());
        bookDose2ResponseDto.setCertificateNo(certificate.getCertificateNo());
        bookDose2ResponseDto.setCertificateMsg(certificate.getConfirmationMessage());
        bookDose2ResponseDto.setMessage("Congratulation! "+person.getName() + " you have fully vaccinated with dose type "+doseType);

        return bookDose2ResponseDto;

    }
}
