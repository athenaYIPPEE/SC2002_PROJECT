package project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        initializePatients();

        // Sample doctors and patients to initialize the system
        Doctor doctor1 = new Doctor("D001", "password", "Doctor", "Dr. Smith");
        Doctor doctor2 = new Doctor("D002", "password", "Doctor", "Dr. Johnson");

        // Simulate setting availability for doctors (keeping Date as per your existing method)
        List<LocalDateTime> availableSlotsDoctor1 = new ArrayList<>();
        availableSlotsDoctor1.add(LocalDateTime.of(2024, 11, 10, 9, 0));
        availableSlotsDoctor1.add(LocalDateTime.of(2024, 11, 10, 10, 0));
        // Using java.util.Date to simulate the existing method
        doctor1.setAvailability(new java.util.Date(), availableSlotsDoctor1);
        
        List<LocalDateTime> availableSlotsDoctor2 = new ArrayList<>();
        availableSlotsDoctor2.add(LocalDateTime.of(2024, 11, 10, 11, 0));
        availableSlotsDoctor2.add(LocalDateTime.of(2024, 11, 10, 14, 0));
        doctor2.setAvailability(new java.util.Date(), availableSlotsDoctor2);

        // Create a PatientsAppointments instance to manage appointments
        PatientsAppointments patientAppointments = new PatientsAppointments(patient);

        // Patient schedules an appointment with a doctor
        patientAppointments.scheduleAppointment();

        // Create an appointment manually in the main method (for testing purposes)
        String appointmentID = "A123";
        String doctorName = doctor1.getName();
        String patientId = patient.getPatientId();
        LocalDateTime appointmentDate = LocalDateTime.of(2024, 11, 10, 9, 0); // Example date
        String status = "Confirmed";
        String serviceType = "Consultation";

        // Manually creating an appointment and adding to the doctor's and patient's list
        Appointment appointment = new Appointment(appointmentID, doctorName, patientId, appointmentDate, status, serviceType);
        doctor1.addAppointment(appointment);  // Adding appointment to doctor's list
        Patients.addAppointment(appointment);  // Adding appointment to patient's list

        // Output the appointment details
        System.out.println("Appointment details: ");
        System.out.println(appointment);

    }

    public static void initializePatients() {
        Patients.patientList = new HashMap<>() {{
            put("P1001", new Patients("P1001", "password1", "Patient", "Alice Brown", "1980-05-14", "F", "1234567890", "alice.brown@example.com", "A+"));
            put("P1002", new Patients("P1002", "password2", "Patient", "Bob Stone", "1975-11-22", "M", "9876543210", "bob.stone@example.com", "B+"));
            put("P1003", new Patients("P1003", "password3", "Patient", "Charlie White", "1990-07-08", "M", "4567891230", "charlie.white@example.com", "O-"));
        }};
    }
}
