package com.booleanuk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @OneToMany
    @JoinColumn(name = "appointment_id")
    @JsonIgnoreProperties("doctor")
    private List<Appointment> appointments;

    public Doctor(int id) {
        this.id = id;
    }

    public void addAppointment(Appointment a) {
        this.appointments.add(a);
    }

}
