package com.booleanuk.api.controller;

import com.booleanuk.api.model.Appointment;
import com.booleanuk.api.model.Doctor;
import com.booleanuk.api.model.Patient;
import com.booleanuk.api.repository.AppointmentRepository;
import com.booleanuk.api.repository.DoctorRepository;
import com.booleanuk.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointments;

    @Autowired
    private PatientRepository patients;

    @Autowired
    private DoctorRepository doctors;

    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody Appointment toAdd) {
        Doctor doctor = this.doctors.findById(toAdd.getDoctor().getId()).
                orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );

        Patient patient = this.patients.findById(toAdd.getPatient().getId()).
                orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );

        toAdd.setDoctor(doctor);
        toAdd.setPatient(patient);

        return new ResponseEntity<>(this.appointments.save(toAdd), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAll() {
        return new ResponseEntity<>(this.appointments.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Appointment> getOne(@PathVariable(name = "id") int id) {
        Appointment toReturn = this.appointments.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

}
