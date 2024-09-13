package com.booleanuk.api.repository;

import com.booleanuk.api.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    public List<Appointment> findAllBydoctorId(int doctorId);
}
