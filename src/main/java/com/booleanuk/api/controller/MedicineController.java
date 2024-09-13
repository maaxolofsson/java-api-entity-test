package com.booleanuk.api.controller;

import com.booleanuk.api.model.Medicine;
import com.booleanuk.api.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicines")
public class MedicineController {

    @Autowired
    private MedicineRepository medicines;

    @PostMapping
    public ResponseEntity<Medicine> create(@RequestBody Medicine toAdd) {
        return new ResponseEntity<>(this.medicines.save(toAdd), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Medicine>> getAll() {
        return new ResponseEntity<>(this.medicines.findAll(), HttpStatus.OK);
    }

}
