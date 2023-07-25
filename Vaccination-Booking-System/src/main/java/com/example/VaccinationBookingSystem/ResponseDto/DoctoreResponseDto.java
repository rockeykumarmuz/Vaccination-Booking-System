package com.example.VaccinationBookingSystem.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DoctoreResponseDto {

    String name;
    String message;

    VaccinationCenterResponseDto vaccinationCenterResponseDto;
}
