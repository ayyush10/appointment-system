# Appointment System

An intuitive backend API built with Spring Boot, designed for scheduling, managing, and tracking appointments efficiently. This system is integrated with AWS EC2 for hosting and AWS DocumentDB for database management, offering a robust and scalable solution.

---

## Features

- **User Registration & Authentication**: Secure user management with sign-up, login, and password reset functionality.
- **Role-Based Access**: Separate interfaces for administrators and general users.
- **Appointment Management**: Users can book, reschedule, and cancel appointments.
- **Admin Dashboard**: Comprehensive admin panel to manage users, view appointments, and generate reports.
- **Notifications**: Email or SMS notifications for appointment confirmations and reminders.
- **Scalable Architecture**: Built using Spring Boot and deployed on AWS EC2, ensuring high availability and performance.

---

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/ayyush10/appointment-system.git
   ```

2. Navigate to the project directory:

   ```bash
   cd appointment-system
   ```

3. Build the project:

   ```bash
   ./mvnw clean install
   ```

4. Configure environment variables:

   - Create an `application.properties` file in the `src/main/resources` directory.
   - Add the required environment variables, such as AWS DocumentDB credentials and EC2 configurations.

5. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

---

## Tech Stack

- **Backend**: Spring Boot (Java)
- **Database**: AWS DocumentDB (MongoDB compatible)
- **Hosting**: AWS EC2 Instance
- **Authentication**: JWT (JSON Web Token)
- **Build Tool**: Maven

---

## Screenshots

Include screenshots of the application showcasing key features like user dashboard, admin panel, and appointment management.

---

## Contributing

Contributions are welcome! Follow these steps to contribute:

1. Fork the repository.
2. Create a new branch:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add your message"
   ```
4. Push to the branch:
   ```bash
   git push origin feature-name
   ```
5. Open a Pull Request.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contact

If you have any questions or suggestions, feel free to reach out:

- **Name**: Ayush Gupta
- **Email**: [ayu1234shgupta@gmail.com](mailto:ayu1234shgupta@gmail.com)


---

Thank you for using the Appointment System! Contributions and feedback are always appreciated.

