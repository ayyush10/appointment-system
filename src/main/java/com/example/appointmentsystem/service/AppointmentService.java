package com.example.appointmentsystem.service;

import com.example.appointmentsystem.model.*;
import com.example.appointmentsystem.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final TimeSlotRepository timeSlotRepository;
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    @Transactional
    public TimeSlot addTimeSlot(TimeSlot timeSlot, String username) {
        User professor = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        if (professor.getRole() != UserRole.PROFESSOR) {
            throw new RuntimeException("Only professors can add time slots");
        }

        timeSlot.setProfessor(professor);
        return timeSlotRepository.save(timeSlot);
    }

    public List<TimeSlot> getAvailableTimeSlots(Long professorId) {
        User professor = userRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
        return timeSlotRepository.findByProfessorAndIsAvailable(professor, true);
    }

    @Transactional
    public Appointment bookAppointment(Long timeSlotId, String username) {
        TimeSlot timeSlot = timeSlotRepository.findById(timeSlotId)
                .orElseThrow(() -> new RuntimeException("Time slot not found"));

        if (!timeSlot.isAvailable()) {
            throw new RuntimeException("Time slot is not available");
        }

        User student = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (student.getRole() != UserRole.STUDENT) {
            throw new RuntimeException("Only students can book appointments");
        }

        timeSlot.setAvailable(false);
        timeSlotRepository.save(timeSlot);

        Appointment appointment = new Appointment();
        appointment.setStudent(student);
        appointment.setProfessor(timeSlot.getProfessor());
        appointment.setTimeSlot(timeSlot);

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public void cancelAppointment(Long appointmentId, String username) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!appointment.getProfessor().equals(user)) {
            throw new RuntimeException("Only the professor can cancel appointments");
        }

        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointment.getTimeSlot().setAvailable(true);
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointments(String username) {
        User student = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return appointmentRepository.findByStudent(student);
    }
}