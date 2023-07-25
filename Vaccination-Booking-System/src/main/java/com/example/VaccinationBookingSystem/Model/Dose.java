package com.example.VaccinationBookingSystem.Model;

import com.example.VaccinationBookingSystem.Enum.DoseType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Dose {

    @Id
    // it is auto incremented primary key in mysql database independent of successful operation
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String doseId;

    @Enumerated(EnumType.STRING)
    DoseType doseType;

    @CreationTimestamp
    Date vaccinationDate;

    // we are creating relationship b/w person nd dose
    @ManyToOne
    @JoinColumn
    Person person;

}
