package com.booleanuk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String dob;

    @OneToMany
    @JoinColumn(name = "appointment_id")
    @JsonIgnoreProperties("patient")
    private List<Appointment> appointments;

    public Patient(int id) {
        this.id = id;
    }

    public void addAppointment(Appointment a) {
        this.appointments.add(a);
    }

}
