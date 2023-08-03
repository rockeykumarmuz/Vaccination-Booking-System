package com.example.VaccinationBookingSystem.ResponseDto;

import com.example.VaccinationBookingSystem.Model.Certificate;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookDose2ResponseDto {

    String personName;

    String message;

    String certificateNo;

    String certificateMsg;

}
