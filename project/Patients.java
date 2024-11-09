package project;

import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Patients extends AllUsers {
    protected String name;
    private String dob;
    protected String phoneNumber;
    protected String emailAddress;
    private Character gender;
    private int age;
    private MedicalRecord medicalRecord; // Encapsulated medical data
    private HashMap<String, Appointment> appointments; // List of appointments for the patient

    // Static HashMap of all patients (name -> Patients object)
    private static HashMap<String, Patients> patientList = new HashMap<>();

    // Constructor
    public Patients(String hospitalId, String password, String name, String dob, Character gender, String phoneNumber, String emailAddress, String bloodType) {
        super(hospitalId, password, "Patient");
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.medicalRecord = new MedicalRecord(bloodType); // Initialize medical record
        this.appointments = new HashMap<>(); // Initialize appointment map
    }

    public void displayMenu(){
        System.out.println("Menu: \n"
				+ "1: View Medical Records \n"
				+ "2: Update Personal Information \n"
				+ "3: View Available Appointment Slots \n"
				+ "4: Schedule an Appointment \n"
				+ "5: Reschedule an Appointment \n"
				+ "6: Cancel an Appointment \n"
				+ "7: View Past Appointment Outcome Records \n"
                + "8: Logout");
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();
		switch(option) {
			case 1 -> DoctorMedicalRecord.viewMedicalRecords();
			case 2 -> {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter new phone number: ");
                String newPhone = scanner.nextLine();
                System.out.print("Enter new email address: ");
                String newEmail = scanner.nextLine();
                PatientsContactInfo.updateContactInfo(this.name, newPhone, newEmail);
            }
			case 3 -> DoctorScheduleService.viewPersonalSchedule();
			case 4 -> doctorScheduleService.setAvailability();
			case 5 -> doctorAppointmentService.AppointmentRequest();
			case 6 -> doctorAppointmentService.viewUpcomingAppointments();
			case 7 -> doctorAppointmentService.recordAppointmentOutcome();
			default -> System.out.println("Invalid option. Please try again.");
		}
    }

    // Static method to initialize the patient list
    public static void initializePatientList() {
        patientList.put("Alice Brown", new Patients("P1001", "password1", "Alice Brown", "1980-05-14", 'F', "1234567890", "alice.brown@example.com", "A+"));
        patientList.put("Bob Stone", new Patients("P1002", "password2", "Bob Stone", "1975-11-22", 'M', "9876543210", "bob.stone@example.com", "B+"));
        patientList.put("Charlie White", new Patients("P1003", "password3", "Charlie White", "1990-07-08", 'M', "4567891230", "charlie.white@example.com", "O-"));
    }

    // Method to get all patients
    public static HashMap<String, Patients> getPatientList() {
        return patientList;
    }

    // Method to find a patient by their name
    public static Patients getPatientByName(String name) {
        return patientList.get(name);
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
}
