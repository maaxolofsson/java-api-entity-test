package com.booleanuk.api.repository;

import com.booleanuk.api.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    public Appointment findAllByDoctorId(int doctorId);
}
