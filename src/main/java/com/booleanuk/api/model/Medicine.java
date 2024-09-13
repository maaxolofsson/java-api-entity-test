package com.booleanuk.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "medicines")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int quantity;

    @Column
    private String note;

    @ManyToMany
    @JoinColumn(name = "prescription_id")
    private List<Prescription> prescriptions;

    public void addPrescription(Prescription p) {
        this.prescriptions.add(p);
    }

}
