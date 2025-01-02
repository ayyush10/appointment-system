package com.example.appointmentsystem.repository;

import com.example.appointmentsystem.model.Appointment;
import com.example.appointmentsystem.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment, Long> {
    List<Appointment> findByStudent(User student);
}