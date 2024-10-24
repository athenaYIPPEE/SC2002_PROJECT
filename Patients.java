package users;

import java.util.ArrayList;
import java.util.Date;

public class Patients extends All_users {
    private String name;
    private String dob;
    private String gender;
    private String phoneNumber;
    private String emailAddress;
    private MedicalRecord medicalRecord; // Encapsulated medical data
    private ArrayList<Appointment> appointments; // List of appointments for the patient

    // Static list of all patients
    private static ArrayList<Patients> patientList = new ArrayList<>();

    // Constructor
    public Patients(String hospitalId, String password, String name, String dob, String gender, String phoneNumber, String emailAddress, String bloodType) {
        super(hospitalId, password, "Patient");
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.medicalRecord = new MedicalRecord(bloodType); // Initialize medical record
        this.appointments = new ArrayList<>(); // Initialize appointment list
    }

    // Method to allow patients to update contact info only
    public void updateContactInfo(String newPhoneNumber, String newEmailAddress) {
        this.phoneNumber = newPhoneNumber;
        this.emailAddress = newEmailAddress;
        System.out.println("Contact information updated for " + this.name);
    }

    // Method to view the encapsulated medical record
    public void viewMedicalRecord() {
        System.out.println("Patient ID: " + this.getHospitalId());
        System.out.println("Name: " + this.name);
        System.out.println("Date of Birth: " + this.dob);
        System.out.println("Gender: " + this.gender);
        System.out.println("Phone Number: " + this.phoneNumber);
        System.out.println("Email Address: " + this.emailAddress);
        System.out.println("Medical Record:");
        medicalRecord.viewMedicalRecord(); // Call method to display medical record details
    }

    // Static method to initialize the patient list
    public static void initializePatientList() {
        patientList.add(new Patients("P1001", "password1", "Alice Brown", "1980-05-14", "Female", "1234567890", "alice.brown@example.com", "A+"));
        patientList.add(new Patients("P1002", "password2", "Bob Stone", "1975-11-22", "Male", "9876543210", "bob.stone@example.com", "B+"));
        patientList.add(new Patients("P1003", "password3", "Charlie White", "1990-07-08", "Male", "4567891230", "charlie.white@example.com", "O-"));
    }

    // Method to get all patients
    public static ArrayList<Patients> getPatientList() {
        return patientList;
    }

    // Method to find a patient by their ID
    public static Patients getPatientById(String id) {
        for (Patients patient : patientList) {
            if (patient.getHospitalId().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    // Appointment Methods (Patients can schedule, cancel, and reschedule appointments)
    public void scheduleAppointment(Appointment appointment) {
        this.appointments.add(appointment);
        System.out.println("Appointment scheduled for " + this.name);
    }

    public void cancelAppointment(String appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                appointment.setStatus("Canceled");
                System.out.println("Appointment " + appointmentId + " has been canceled.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }

    public void rescheduleAppointment(String appointmentId, Date newDate, String newTimeSlot) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                appointment.setAppointmentDate(newDate);
                appointment.setTimeSlot(newTimeSlot);
                System.out.println("Appointment " + appointmentId + " has been rescheduled.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }

    // Method to view all appointments for the patient
    public void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment.toString());
            }
        }
    }
}
