package users;

import java.util.ArrayList;
import java.util.Date;

public class Patients extends All_users {
    // Existing fields
    private String name;
    private String dob;
    private String gender;
    private String bloodType;
    private String phoneNumber;
    private String emailAddress;
    private ArrayList<String> diagnoses;
    private ArrayList<String> treatments;
    private ArrayList<Appointment> appointments; // List of appointments for the patient

    // Static list of all patients
    private static ArrayList<Patients> patientList = new ArrayList<>();

    // Constructor
    public Patients(String hospitalId, String password, String name, String dob, String gender, String bloodType, String phoneNumber, String emailAddress) {
        super(hospitalId, password, "Patient");
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.bloodType = bloodType;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.diagnoses = new ArrayList<>();
        this.treatments = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    // Method to initialize patient list directly in the Patient class
    public static void initializePatientList() {
        patientList.add(new Patients("P1001", "password1", "Alice Brown", "1980-05-14", "Female", "A+", "1234567890", "alice.brown@example.com"));
        patientList.add(new Patients("P1002", "password2", "Bob Stone", "1975-11-22", "Male", "B+", "9876543210", "bob.stone@example.com"));
        patientList.add(new Patients("P1003", "password3", "Charlie White", "1990-07-08", "Male", "O-", "4567891230", "charlie.white@example.com"));
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

    // Appointment Methods
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

    public void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment.toString());
            }
        }
    }

    // View Personal and Medical Information
    public void viewMedicalRecord() {
        System.out.println("Patient ID: " + this.getHospitalId());
        System.out.println("Name: " + this.name);
        System.out.println("Date of Birth: " + this.dob);
        System.out.println("Gender: " + this.gender);
        System.out.println("Blood Type: " + this.bloodType);
        System.out.println("Phone Number: " + this.phoneNumber);
        System.out.println("Email Address: " + this.emailAddress);
        System.out.println("Diagnoses: " + this.diagnoses);
        System.out.println("Treatments: " + this.treatments);
        System.out.println("Appointments:");
        viewAppointments(); // Call the method to display appointments
    }
}
