package com.example.appointmentsystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "time_slots") // Defines a MongoDB collection
public class TimeSlot {

    @Id // Marks this field as the unique identifier for MongoDB
    private String id;

    @DBRef // References a User document
    private User professor;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isAvailable = true; // Default value
}
