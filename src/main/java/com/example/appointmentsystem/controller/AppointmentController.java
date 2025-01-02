package com.example.appointmentsystem.controller;

import com.example.appointmentsystem.model.*;
import com.example.appointmentsystem.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<Map<String, String>> getApiInfo() {
        return ResponseEntity.ok(Map.of(
                "status", "API is running",
                "version", "1.0",
                "endpoints", """
                GET /api/appointments - This info
                POST /api/appointments/timeslots - Add new time slot (Professor only)
                GET /api/appointments/professors/{professorId}/timeslots - Get available time slots
                POST /api/appointments/book - Book an appointment (Student only)
                POST /api/appointments/{appointmentId}/cancel - Cancel an appointment (Professor only)
                GET /api/appointments/my-appointments - Get user's appointments
                """
        ));
    }

    @PostMapping("/timeslots")
    public ResponseEntity<TimeSlot> addTimeSlot(@RequestBody TimeSlot timeSlot, Authentication auth) {
        return ResponseEntity.ok(appointmentService.addTimeSlot(timeSlot, auth.getName()));
    }

    @GetMapping("/professors/{professorId}/timeslots")
    public ResponseEntity<List<TimeSlot>> getAvailableTimeSlots(@PathVariable Long professorId) {
        return ResponseEntity.ok(appointmentService.getAvailableTimeSlots(professorId));
    }

    @PostMapping("/book")
    public ResponseEntity<Appointment> bookAppointment(@RequestParam Long timeSlotId, Authentication auth) {
        return ResponseEntity.ok(appointmentService.bookAppointment(timeSlotId, auth.getName()));
    }

    @PostMapping("/{appointmentId}/cancel")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long appointmentId, Authentication auth) {
        appointmentService.cancelAppointment(appointmentId, auth.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my-appointments")
    public ResponseEntity<List<Appointment>> getMyAppointments(Authentication auth) {
        return ResponseEntity.ok(appointmentService.getAppointments(auth.getName()));
    }
}