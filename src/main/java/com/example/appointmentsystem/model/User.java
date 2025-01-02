package com.example.appointmentsystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users") // Defines a MongoDB collection
public class User {

    @Id // Marks this field as the unique identifier for MongoDB
    private String id;

    private String username;
    private String password;

    private UserRole role; // MongoDB will store this as a string
}
