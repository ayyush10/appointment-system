package com.example.appointmentsystem;

import com.example.appointmentsystem.model.*;
import com.example.appointmentsystem.repository.*;
import com.example.appointmentsystem.util.TestLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Disabled
public class AppointmentSystemE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TestLogger testLogger;

    @BeforeEach
    void setUp() {
        // Clean up all data before each test
        appointmentRepository.deleteAll();
        timeSlotRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "professor1", roles = "PROFESSOR")
    public void testCompleteAppointmentFlow() throws Exception {
        testLogger.logTestStep("Setting up test users");
        // Setup test users
        User professor = new User();
        professor.setUsername("professor1");
        professor.setRole(UserRole.PROFESSOR);
        userRepository.save(professor);
        testLogger.logTestResult("Created professor user", true);

        User student1 = new User();
        student1.setUsername("student1");
        student1.setRole(UserRole.STUDENT);
        userRepository.save(student1);
        testLogger.logTestResult("Created student1 user", true);

        User student2 = new User();
        student2.setUsername("student2");
        student2.setRole(UserRole.STUDENT);
        userRepository.save(student2);
        testLogger.logTestResult("Created student2 user", true);

        // Rest of the test remains the same...
        // Professor adds time slots
        testLogger.logTestStep("Professor adding time slots");
        LocalDateTime startTime = LocalDateTime.now().plusDays(1);
        LocalDateTime endTime = startTime.plusHours(1);
        String timeSlotJson = String.format(
                "{\"startTime\":\"%s\",\"endTime\":\"%s\"}",
                startTime.format(DateTimeFormatter.ISO_DATE_TIME),
                endTime.format(DateTimeFormatter.ISO_DATE_TIME)
        );

        mockMvc.perform(post("/api/appointments/timeslots")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeSlotJson))
                .andExpect(status().isOk());
        testLogger.logTestResult("Professor added time slot", true);

        // Student1 views available time slots
        testLogger.logTestStep("Student1 viewing available time slots");
        MvcResult viewResult = mockMvc.perform(get("/api/appointments/professors/" + professor.getId() + "/timeslots")
                        .with(user("student1").roles("STUDENT")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].available").value(true))
                .andReturn();
        testLogger.logTestResult("Student1 viewed available time slots", true);

        // Student1 books appointment
        testLogger.logTestStep("Student1 booking appointment");
        Long timeSlotId = Long.valueOf(timeSlotRepository.findByProfessorAndIsAvailable(professor, true).get(0).getId());
        mockMvc.perform(post("/api/appointments/book")
                        .with(user("student1").roles("STUDENT"))
                        .param("timeSlotId", timeSlotId.toString()))
                .andExpect(status().isOk());
        testLogger.logTestResult("Student1 booked appointment", true);

        // Professor cancels appointment
        testLogger.logTestStep("Professor cancelling appointment");
        Long appointmentId = Long.valueOf(appointmentRepository.findByStudent(student1).get(0).getId());
        mockMvc.perform(post("/api/appointments/" + appointmentId + "/cancel"))
                .andExpect(status().isOk());
        testLogger.logTestResult("Professor cancelled appointment", true);

        // Student1 checks appointments
        testLogger.logTestStep("Student1 checking appointments");
        mockMvc.perform(get("/api/appointments/my-appointments")
                        .with(user("student1").roles("STUDENT")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("CANCELLED"));
        testLogger.logTestResult("Student1 verified cancelled appointment", true);
    }
}