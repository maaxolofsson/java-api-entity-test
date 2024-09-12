package com.booleanuk.api.controller;

import com.booleanuk.api.model.Patient;
import com.booleanuk.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository patients;

    @GetMapping
    public ResponseEntity<List<Patient>> getAll() {
        return new ResponseEntity<>(this.patients.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Patient> getOne(@PathVariable(name = "id") int id) {
        Patient patient = this.patients.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Patient> create(@RequestBody Patient patient) {
        return new ResponseEntity<>(this.patients.save(patient), HttpStatus.CREATED);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Patient>> createBulk(@RequestBody List<Patient> patients) {
        return new ResponseEntity<>(this.patients.saveAll(patients), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Patient> delete(@PathVariable(name = "id") int id) {
        Patient toDelete = this.patients.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
        this.patients.delete(toDelete);
        return new ResponseEntity<>(toDelete, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Patient> update(@PathVariable(name = "id") int id, @RequestBody Patient newData) {
        Patient toUpdate = this.patients.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );

        toUpdate.setDob(newData.getDob());
        toUpdate.setFirstName(newData.getFirstName());
        toUpdate.setLastName(newData.getLastName());

        return new ResponseEntity<>(this.patients.save(toUpdate), HttpStatus.CREATED);
    }

}



