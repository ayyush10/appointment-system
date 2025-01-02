package com.example.appointmentsystem.repository;

import com.example.appointmentsystem.model.TimeSlot;
import com.example.appointmentsystem.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TimeSlotRepository extends MongoRepository<TimeSlot, Long> {
    List<TimeSlot> findByProfessorAndIsAvailable(User professor, boolean isAvailable);
}