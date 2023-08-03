package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Dto.DoctorRequestDto;
import com.example.VaccinationBookingSystem.Dto.GetAllDoctorNameAgeGreaterThanRequestDto;
import com.example.VaccinationBookingSystem.Enum.Gender;
import com.example.VaccinationBookingSystem.Exception.CentreNotFoundException;
import com.example.VaccinationBookingSystem.Exception.DoctorNotFoundException;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.VaccinationCenter;
import com.example.VaccinationBookingSystem.Repositry.DoctorRepositry;
import com.example.VaccinationBookingSystem.Repositry.VaccinationCenterRepositry;
import com.example.VaccinationBookingSystem.ResponseDto.DoctoreResponseDto;
import com.example.VaccinationBookingSystem.ResponseDto.GetAllDoctorNameAgeGreaterThanResponseDto;
import com.example.VaccinationBookingSystem.ResponseDto.VaccinationCenterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

@Service
public class DoctoreService {
    class Pair{
        Doctor doctor;
        int size;

        Pair(Doctor doctor, int size) {
            this.doctor = doctor;
            this.size = size;
        }
    }

    @Autowired
    DoctorRepositry doctorRepositry;
    @Autowired
    VaccinationCenterRepositry vaccinationCenterRepositry;
    public DoctoreResponseDto addDoctor(DoctorRequestDto doctorRequestDto) {

        Optional<VaccinationCenter> optionalCenter = vaccinationCenterRepositry.findById(doctorRequestDto.getCentreId());

        if(!optionalCenter.isPresent()) {
            throw new CentreNotFoundException("sorry! wrong center Id");
        }

        VaccinationCenter vaccinationCenter = optionalCenter.get();

        // now create another entity
        Doctor doctor= new Doctor();
        doctor.setName(doctorRequestDto.getDoctorName());
        doctor.setAge(doctorRequestDto.getAge());
        doctor.setGender(doctorRequestDto.getGender());
        doctor.setEmailId(doctorRequestDto.getEmailId());
        doctor.setVaccinationCenter(vaccinationCenter);

        // add doctors into the center
        vaccinationCenter.getDoctors().add(doctor);

        // this will save doctor and center as well because we are saving parent so child will be saved automatically
        VaccinationCenter savedCenter= vaccinationCenterRepositry.save(vaccinationCenter);

        DoctoreResponseDto doctoreResponseDto= new DoctoreResponseDto();

        // first we are getting saved doctors list and latest doctor is size-1
        List<Doctor> doctors= savedCenter.getDoctors();
        Doctor latestSavedDoctor = doctors.get(doctors.size()-1);

        doctoreResponseDto.setName(latestSavedDoctor.getName());
        doctoreResponseDto.setMessage("Congrats! you have been registered");

        //adding vaccination center response
        VaccinationCenterResponseDto vaccinationCenterResponseDto=new VaccinationCenterResponseDto();

        vaccinationCenterResponseDto.setCentreName(savedCenter.getCenterName());
        vaccinationCenterResponseDto.setAddress(savedCenter.getAddress());
        vaccinationCenterResponseDto.setCenterType(savedCenter.getCenterType());
        vaccinationCenterResponseDto.setMessage(("Congrats! you have alloted this center to serve"));

        doctoreResponseDto.setVaccinationCenterResponseDto(vaccinationCenterResponseDto);

        return doctoreResponseDto;
    }

    public GetAllDoctorNameAgeGreaterThanResponseDto getAgeGreaterThan(GetAllDoctorNameAgeGreaterThanRequestDto getAllDoctorNameAgeGreaterThanRequestDto) {
        List<Doctor> doctors= doctorRepositry.findByAgeGreaterThan( getAllDoctorNameAgeGreaterThanRequestDto.getAge());

        if(doctors.size()==0) {
            throw new DoctorNotFoundException("Please enter the valid age");
        }

        // converting entity to response again
        GetAllDoctorNameAgeGreaterThanResponseDto doctorResponse = new GetAllDoctorNameAgeGreaterThanResponseDto();

        List<String> al= new ArrayList<>();

        // iteration over doctor and adding all doctor names into the list
        for(Doctor doctor : doctors) {
           // doctorResponse.getDoctors().add(doctor.getName());
            al.add(doctor.getName());
        }

        doctorResponse.setDoctors(al);

        return doctorResponse;
    }

    public List<String> getDoctorWithHighestNumberOfAppointments() {

        List<Doctor> doctorList = doctorRepositry.findAll();

        PriorityQueue<Pair> pq= new PriorityQueue<>((a, b)->{
            return b.size - a.size;
        });

        for(Doctor doctor: doctorList) {
            pq.add(new Pair(doctor, doctor.getAppointments().size()));
        }

        List<String > al= new ArrayList<>();
        int size = 0;

        if(pq.size()!=0) {
            Pair curr = pq.remove();
            size = curr.size;
            al.add(curr.doctor.getName());
        }

        while(pq.size()!=0 && pq.peek().size>=size) {
            Pair nn = pq.remove();
            size= nn.size;
            al.add(nn.doctor.getName());
        }

        return al;

    }
}
