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

import java.time.LocalDateTime;
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

        // Checks if the doctor or patient has a booked appointment at that time
        if (!this.checkAvailability(doctor, patient, toAdd.getAppointmentDate())) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        toAdd.setDoctor(doctor);
        toAdd.setPatient(patient);

        doctor.addAppointment(toAdd);
        patient.addAppointment(toAdd);

        return new ResponseEntity<>(this.appointments.save(toAdd), HttpStatus.CREATED);
    }

    private boolean checkAvailability(Doctor doctor, Patient patient, LocalDateTime dateAndTime) {
        for (Appointment a : doctor.getAppointments()) {
            if (a.getAppointmentDate().toString().equals(dateAndTime.toString())) {
                return false;
            }
        }

        for (Appointment a : patient.getAppointments()) {
            if (a.getAppointmentDate().toString().equals(dateAndTime.toString())) {
                return false;
            }
        }
        
        return true;
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

    @GetMapping("/doctors/{id}") // id = id of the doctor
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorId(@PathVariable(name = "id") int id) {
        Doctor doctor = this.doctors.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );

        return new ResponseEntity<>(this.appointments.findAllBydoctorId(id), HttpStatus.OK);
    }

    @GetMapping("/patients/{id}") // id = id of the patient
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable(name = "id") int id) {
        Doctor doctor = this.doctors.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );

        return new ResponseEntity<>(this.appointments.findAllBydoctorId(id), HttpStatus.OK);
    }

}
