package com.booleanuk.api.controller;

import com.booleanuk.api.model.Appointment;
import com.booleanuk.api.model.Doctor;
import com.booleanuk.api.repository.AppointmentRepository;
import com.booleanuk.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctors;

    @Autowired
    private AppointmentRepository appointments;

    @GetMapping
    public ResponseEntity<List<Doctor>> getAll() {
        return new ResponseEntity<>(this.doctors.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Doctor> getOne(@PathVariable(name = "id") int id) {
        Doctor toReturn = this.doctors.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );

        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Doctor> create(@RequestBody Doctor toAdd) {
        return new ResponseEntity<>(this.doctors.save(toAdd), HttpStatus.CREATED);
    }

    //@PutMapping("{id}")
    public ResponseEntity<Doctor> update(@PathVariable(name = "id") int id, Doctor newData) {
        Doctor toUpdate = this.doctors.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );

        toUpdate.setName(newData.getName());

        return new ResponseEntity<>(this.doctors.save(toUpdate), HttpStatus.CREATED);
    }

    //@DeleteMapping("{id}")
    public ResponseEntity<Doctor> delete(@PathVariable(name = "id") int id) {
        Doctor toDelete = this.doctors.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );

        this.doctors.delete(toDelete);

        return new ResponseEntity<>(toDelete, HttpStatus.OK);
    }

    @GetMapping("{id}/appointments")
    public ResponseEntity<Appointment> getAppointsments(@PathVariable(name = "id") int id) {
        Doctor doctor = this.doctors.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );

        return new ResponseEntity<>(this.appointments.findAllByDoctorId(id), HttpStatus.OK);
    }


}


