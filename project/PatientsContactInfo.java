package project;

import java.util.*;

public class PatientsContactInfo {
    private String phoneNumber;
    private String emailAddress;
    private HashMap<String, PatientsContactInfo> contactInfoMap = new HashMap<>();

    public PatientsContactInfo(String phoneNumber, String emailAddress) {
       this.phoneNumber = phoneNumber;
       this.emailAddress = emailAddress;
    }

    // Static method to update contact information for a specific patient by name
    public static void updatePhoneNumber(PatientsContactInfo patient) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("New Phone Number: ");
        String newPhoneNumber = scanner.nextLine();
        patient.phoneNumber = newPhoneNumber;
        MedicalRecord.updatePhoneNumber(newPhoneNumber);
        System.out.println("Updated Phone Number: "+ newPhoneNumber);
        System.out.println("Email Address" + patient.emailAddress);
}
    public static void updateEmailAddress(PatientsContactInfo patient){
        Scanner scanner = new Scanner(System.in);
        System.out.println("New Email Address: ");
        String newEmailAddress = scanner.nextLine();
        patient.emailAddress = newEmailAddress;
        MedicalRecord.updateEmailAddress(newEmailAddress);
        System.out.println("Updated Email Address: "+ newEmailAddress);
        System.out.println("Phone Number: " + patient.phoneNumber);
    }
}
