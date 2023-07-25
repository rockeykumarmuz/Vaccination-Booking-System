package com.example.VaccinationBookingSystem.Model;

import com.example.VaccinationBookingSystem.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Person {

    @Id
    // it is auto incremented primary key in mysql database independent of successful operation
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    int age;

    @Column(unique = true, nullable = false)
    String emailId;

    // mysql don't know enum so we need to make it string
    @Enumerated(EnumType.STRING)
    Gender gender;

    boolean Dose1Taken;

    boolean Dose2Taken;

    // person is the obj for i am mapping parent to child
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Dose> doseTaken = new ArrayList<>();

    @OneToOne
    @JoinColumn
    Certificate certificate;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Appointment> appointments = new ArrayList<>();
}
