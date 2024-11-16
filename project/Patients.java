package project;

import java.util.*;

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
    private List<Appointment> appointments; // List of appointments for the patient
    private AppointmentSlots appointmentSlots;

    // Static HashMap of all patients (name -> Patients object)
    protected static HashMap<String, Patients> patientList = new HashMap<>();
    public static List<Patients> patients = new ArrayList<>();
    private PatientsAppointments patientAppointments;

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
        this.appointments = new ArrayList<>();
        patients.add(this);
        this.patientAppointments = new PatientsAppointments(this);
    }

    public void displayMenu() {
        int option;  // Declare the option variable outside the loop
        do {
            System.out.println("Menu: \n"
                    + "1: View Medical Records \n"
                    + "2: Update Personal Information \n"
                    + "3: View Available Appointment Slots \n"
                    + "4: Schedule an Appointment \n"
                    + "5: Reschedule an Appointment \n"
                    + "6: Cancel an Appointment \n"
                    + "7: View Scheduled Appointment \n"
                    + "8: View Past Appointment Records \n"
                    + "9: Logout");
    
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt(); // Take user input for menu option
    
            switch (option) {
                case 1 -> {
                    medicalRecord.displayRecords(hospitalId);
                    break;
                }
                case 2 -> {
                    System.out.println("1. Update Phone Number   2. Update Email Address");
                    Scanner scanner = new Scanner(System.in);
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            contactInfo.updatePhoneNumber(hospitalId);
                            break;
                        case 2:
                            contactInfo.updateEmailAddress(hospitalId);
                            break;
                        default:
                            System.out.println("Invalid Choice.");
                            break;
                    }
                    break;
                }
                case 3 -> {
                    System.out.println("Select a Doctor: ");
                    for (int i = 0; i < Doctor.doctorNames.size(); i++) {
                        System.out.println((i + 1) + ". Dr. " + Doctor.doctorNames.get(i));
                    }
                    Scanner scanner = new Scanner(System.in);
                    int choose = scanner.nextInt() - 1;
                    Doctor selectedDoctor = Doctor.doctors.get(choose);
                    AppointmentSlots.viewAppointmentSlots(selectedDoctor.getName());
                    break;
                }
                case 4 -> {
                    patientAppointments.scheduleAppointment();
                    break;
                }
                case 5 -> {
                    System.out.println("Enter Appointment ID to reschedule: ");
                    Scanner scanner = new Scanner(System.in);
                    String id = scanner.nextLine();
                    patientAppointments.rescheduleAppointment(id);
                    break;
                }
                case 6 -> {
                    System.out.println("Enter Appointment ID to cancel: ");
                    Scanner scanner = new Scanner(System.in);
                    String id = scanner.nextLine();
                    patientAppointments.cancelAppointment(id);
                    break;
                }
                case 7 -> {
                    patientAppointments.viewScheduledAppointments();
                    break;
                }
                case 8 -> {
                    patientAppointments.viewPastRecords();
                    break;
                }
                case 9 -> {
                    System.out.println("Logging out...");
                    break; // Exit the loop when user selects 9
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 9); // Continue looping until the user selects "9" to log out
    }
    

    public String getPatientId(){
        return hospitalId;
    }

    public String getName(){
        return name;
    }

    public String getDob(){
        return dob;
    }

    public String getGender(){
        return gender;
    }

    public String getEmail(){
        return emailAddress;
    }

    public String getPhone(){
        return phoneNumber;
    }

    public String getBlood(){
        return bloodType;
    }

    public void addAppointment(Appointment appointment) {
		this.appointments.add(appointment); // Add the appointment to the doctor's list
    }

    
    public List<Appointment> getAppointments() {
        return appointments;  // Return the list of appointments
    }

    // Static method to initialize the patient list
    /*public static void initializePatientList() {
        patientList.put("Alice Brown", new Patients("P1001", "password1", "Alice Brown", "1980-05-14", 'F', "1234567890", "alice.brown@example.com", "A+"));
        patientList.put("Bob Stone", new Patients("P1002", "password2", "Bob Stone", "1975-11-22", 'M', "9876543210", "bob.stone@example.com", "B+"));
        patientList.put("Charlie White", new Patients("P1003", "password3", "Charlie White", "1990-07-08", 'M', "4567891230", "charlie.white@example.com", "O-"));
    }*/

    // Method to get all patients
    public static HashMap<String, Patients> getPatientList() {
        return patientList;
    }

}
