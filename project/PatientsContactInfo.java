package project;

import java.util.*;

public class PatientsContactInfo {
    private String phoneNumber;
    private String emailAddress;
    protected static HashMap<String, PatientsContactInfo> contactInfoMap = new HashMap<>();
    //private MedicalRecord medicalRecord;
    private static HashMap<String, MedicalRecord> patientMedicalRecordMap;

    public PatientsContactInfo(String phoneNumber, String emailAddress) {
       this.phoneNumber = phoneNumber;
       this.emailAddress = emailAddress;
       this.patientMedicalRecordMap = MedicalRecord.patientRecordMap;
    }

    // Static method to update contact information for a specific patient by name
    public void updatePhoneNumber(String hospitalId) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("New Phone Number: ");
        String newPhoneNumber = scanner.nextLine();

        PatientsContactInfo patientContactInfo = contactInfoMap.get(hospitalId);
        if (patientContactInfo != null) {
            patientContactInfo.phoneNumber = newPhoneNumber;
            patientMedicalRecordMap.get(hospitalId).updatePhoneNumber(newPhoneNumber);
            //medicalRecord.updatePhoneNumber(newPhoneNumber);
        } else {
            System.out.println("Patient not found.");
        }

        System.out.println("Updated Phone Number: "+ newPhoneNumber);
        System.out.println("Email Address: " + patientContactInfo.emailAddress);
}
    public void updateEmailAddress(String hospitalId){
        Scanner scanner = new Scanner(System.in);
        System.out.println("New Email Address: ");
        String newEmailAddress = scanner.nextLine();

        PatientsContactInfo patientContactInfo = contactInfoMap.get(hospitalId);
        if (patientContactInfo != null) {
            patientContactInfo.emailAddress = newEmailAddress;
            patientMedicalRecordMap.get(hospitalId).updateEmailAddress(newEmailAddress);
        } else {
            System.out.println("Patient not found.");
        }
        //MedicalRecord.updateEmailAddress(newEmailAddress);
        System.out.println("Updated Email Address: "+ newEmailAddress);
        System.out.println("Phone Number: " + patientContactInfo.phoneNumber);
    }
}
