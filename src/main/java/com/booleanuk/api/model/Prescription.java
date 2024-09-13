package com.booleanuk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToMany
    @JoinColumn(name = "medicine_id")
    @JsonIgnoreProperties("prescriptions")
    private List<Medicine> medicines;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    public void addMedicine(Medicine m) {
        this.medicines.add(m);
    }

}
