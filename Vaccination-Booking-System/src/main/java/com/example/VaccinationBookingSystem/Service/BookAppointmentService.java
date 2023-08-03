package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Exception.DoctorNotFoundException;
import com.example.VaccinationBookingSystem.Exception.personNotFoundException;
import com.example.VaccinationBookingSystem.Model.Appointment;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.Model.VaccinationCenter;
import com.example.VaccinationBookingSystem.Repositry.BookAppointmentRepositry;
import com.example.VaccinationBookingSystem.Repositry.DoctorRepositry;
import com.example.VaccinationBookingSystem.Repositry.PersonRepositry;
import com.example.VaccinationBookingSystem.ResponseDto.BookAppointmentRequestDto;
import com.example.VaccinationBookingSystem.ResponseDto.BookAppointmentResponseDto;
import com.example.VaccinationBookingSystem.ResponseDto.VaccinationCenterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookAppointmentService {

    @Autowired
    BookAppointmentRepositry bookAppointmentRepositry;

    @Autowired
    PersonRepositry personRepositry;

    @Autowired
    DoctorRepositry doctorRepositry;

    @Autowired
    JavaMailSender javaMailSender;

    public BookAppointmentResponseDto bookAppointment(BookAppointmentRequestDto bookAppointmentRequestDto) throws personNotFoundException {

        Optional<Person> optionalPerson = personRepositry.findById(bookAppointmentRequestDto.getPersonId());

        if(!optionalPerson.isPresent()) {
            throw new personNotFoundException("sorry! Person doesn't exist");
        }

        Optional<Doctor> optionalDoctor = doctorRepositry.findById(bookAppointmentRequestDto.getDoctorId());

        if(!optionalDoctor.isPresent()) {
            throw new DoctorNotFoundException("sorry! Doctor doesn't exist");
        }

        Person person = optionalPerson.get();
        Doctor doctor = optionalDoctor.get();

        Appointment appointment= new Appointment();

        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));
        appointment.setDoctor(doctor);
        appointment.setPerson(person);

        // we are saving first appointment then we will set person and doctor else wo without primary key se 2 times db me add hoga
        Appointment savedAppointment = bookAppointmentRepositry.save(appointment);

        // just go in doctor class and check all parameter to add or not
        doctor.getAppointments().add(savedAppointment);
        person.getAppointments().add(savedAppointment);

        Person savedPerson = personRepositry.save(person);
        Doctor savedDoctor = doctorRepositry.save(doctor);

        // getting saved vaccination center
        VaccinationCenter vaccinationCenter = savedDoctor.getVaccinationCenter();

        String text = "Congrats! " + savedPerson.getName() + " Your appointment has been booked with "+savedDoctor.getName() + ", Your vaccination Center "+vaccinationCenter.getCenterName()
         + " Please reach at this " + vaccinationCenter.getAddress() + " at this time and Date "+ savedAppointment.getAppointmentDate() + ", and at this time. Thank you from Govt of India. Stay healthy and keep maintain social distancing.";

        // simple mailsender
        SimpleMailMessage simpleMailMessage =new SimpleMailMessage();
        simpleMailMessage.setFrom("rockeykumarsoftengg@gmail.com");
        simpleMailMessage.setTo(savedPerson.getEmailId());
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

        //  setting response in dto
        BookAppointmentResponseDto bookAppointmentResponseDto=new BookAppointmentResponseDto();
        bookAppointmentResponseDto.setPersonName(savedPerson.getName());
        bookAppointmentResponseDto.setDoctorName(savedDoctor.getName());
        bookAppointmentResponseDto.setAppointmentId(savedAppointment.getAppointmentId());
        bookAppointmentResponseDto.setAppointmentDate(savedAppointment.getAppointmentDate());

        VaccinationCenterResponseDto vaccinationCenterResponseDto = new VaccinationCenterResponseDto();


        vaccinationCenterResponseDto.setCenterType(vaccinationCenter.getCenterType());
        vaccinationCenterResponseDto.setCentreName(vaccinationCenter.getCenterName());
        vaccinationCenterResponseDto.setAddress(vaccinationCenter.getAddress());
        vaccinationCenterResponseDto.setMessage("congrats! you have been registered for vaccination. please, reach at the specified location on time Thank you!");

        bookAppointmentResponseDto.setVaccinationCenterResponseDto(vaccinationCenterResponseDto);

        return bookAppointmentResponseDto;

    }
}
