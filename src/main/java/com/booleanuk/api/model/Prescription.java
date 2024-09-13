package com.booleanuk.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "prescriptions")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int quantity;

    @ManyToMany
    @JoinColumn(name = "medicine_id")
    private List<Medicine> medicines;

    public void addMedicine(Medicine m) {
        this.medicines.add(m);
    }

}
