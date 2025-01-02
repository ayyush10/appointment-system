package com.example.appointmentsystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "appointments") // Defines a MongoDB collection
public class Appointment {

    @Id // Marks this field as the document's unique identifier
    private String id;

    @DBRef // References another document in the "users" collection
    private User student;

    @DBRef // References another document in the "users" collection
    private User professor;

    @DBRef // References another document in the "timeslots" collection
    private TimeSlot timeSlot;

    private AppointmentStatus status = AppointmentStatus.SCHEDULED; // Default value
}
