package com.example.VaccinationBookingSystem.ResponseDto;

import com.example.VaccinationBookingSystem.Enum.CenterType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VaccinationCenterResponseDto {

    String centreName;

    CenterType centerType;

    String address;

    String message;
}
