package com.example.VaccinationBookingSystem.ResponseDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookDose1ResponseDto {

    String personName;

    String message;

    String certificateNo;

    String certificateMsg;

}
