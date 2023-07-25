package com.example.VaccinationBookingSystem.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetMalesAgeGreaterThanResponseDto {

    List<String> getMales;
}
