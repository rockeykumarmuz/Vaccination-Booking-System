package com.example.VaccinationBookingSystem.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AddPersonResponseDto {

    String name;

    String message;

}
