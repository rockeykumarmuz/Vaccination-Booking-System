package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Dto.VaccinationCenterRequestDto;
import com.example.VaccinationBookingSystem.Enum.CenterType;
import com.example.VaccinationBookingSystem.Model.VaccinationCenter;
import com.example.VaccinationBookingSystem.Repositry.VaccinationCenterRepositry;
import com.example.VaccinationBookingSystem.ResponseDto.VaccinationCenterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationCenterService {

    @Autowired
    VaccinationCenterRepositry vaccinationCenterRepositry;

    public VaccinationCenterResponseDto addCenter(VaccinationCenterRequestDto vaccinationCenterRequestDto) {

        VaccinationCenter vaccinationCenter = new VaccinationCenter();

        // dto to entity conversion
        vaccinationCenter.setCenterName(vaccinationCenterRequestDto.getCenterName());
        vaccinationCenter.setCenterType(vaccinationCenterRequestDto.getCenterType());
        vaccinationCenter.setAddress(vaccinationCenterRequestDto.getAddress());

        VaccinationCenter  center = vaccinationCenterRepositry.save(vaccinationCenter);

        // again entity to dto conversion
        VaccinationCenterResponseDto vaccinationCenterResponseDto= new VaccinationCenterResponseDto();

        vaccinationCenterResponseDto.setCenterType(center.getCenterType());
        vaccinationCenterResponseDto.setAddress(center.getAddress());
        vaccinationCenterResponseDto.setCentreName(center.getCenterName());
        vaccinationCenterResponseDto.setMessage("Successfully Vaccination center added");

        return vaccinationCenterResponseDto;

    }

}
