package com.booleanuk.api.controller;

import com.booleanuk.api.model.Appointment;
import com.booleanuk.api.repository.AppointmentRepository;
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

    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody Appointment toAdd) {
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
