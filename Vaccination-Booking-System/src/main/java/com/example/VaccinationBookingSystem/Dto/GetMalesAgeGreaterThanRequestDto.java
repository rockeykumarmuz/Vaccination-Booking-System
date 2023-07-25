package com.example.VaccinationBookingSystem.Dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetMalesAgeGreaterThanRequestDto {

    int age;
}
