package project;

import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Patients extends AllUsers {
    protected String name;
    private String dob;
    protected String phoneNumber;
    protected String emailAddress;
    private String gender;
    private String hospitalId;
    private String bloodType;
    private MedicalRecord medicalRecord; // Encapsulated medical data
    private PatientsContactInfo contactInfo;
    private HashMap<String, Appointment> appointments; // List of appointments for the patient

    // Static HashMap of all patients (name -> Patients object)
    private static HashMap<String, Patients> patientList = new HashMap<>();

    // Constructor
    public Patients(String hospitalId, String password, String role, String name, String dob, String gender, String phoneNumber, String emailAddress, String bloodType) {
        super(hospitalId, password, "Patient");
        this.hospitalId = hospitalId;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.bloodType = bloodType;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.medicalRecord = new MedicalRecord(hospitalId, name, dob, gender, bloodType, phoneNumber, emailAddress); // Initialize medical record
        this.contactInfo = new PatientsContactInfo(phoneNumber, emailAddress);
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
			case 1 -> MedicalRecord.displayRecords(hospitalId);
			case 2 -> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("1. Update Phone Number   2. Update Email Address");
                int choice = scanner.nextInt();
                switch(choice){
                    case 1: 
                        PatientsContactInfo.updatePhoneNumber(contactInfo);
                        break;
                    case 2:
                        PatientsContactInfo.updateEmailAddress(contactInfo);
                        break;
                    default:
                        System.out.println("Invalid Choice.");
                }
            }
            case 3 -> {

            }
			default -> System.out.println("Invalid option. Please try again.");
		}
    }

    public String getPatientId(){
        return hospitalId;
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

}
