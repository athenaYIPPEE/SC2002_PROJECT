package project;

import java.util.*;

public class MedicalRecord {
    private String patientId;
    private String patientName;
    private String dob;
    private String gender;
    private String bloodType;
    private static String phoneNumber;
    private static String emailAddress;
    private ArrayList<String> diagnoses;
    private ArrayList<String> treatments;
    private ArrayList<String> prescription;
        
        private static HashMap<String, MedicalRecord> patientRecordMap = new HashMap<>();
    
        // Constructor
        public MedicalRecord(String patientId, String patientName, String dob, String gender, String bloodType, String phoneNumber, String emailAddress) {
            this.patientId = patientId;
            this.bloodType = bloodType;
            this.phoneNumber = phoneNumber;
            this.emailAddress = emailAddress;
            this.patientName = patientName;
            this.dob = dob;
            this.gender = gender;
            this.diagnoses = new ArrayList<>();
            this.treatments = new ArrayList<>();
            this.prescription = new ArrayList<>();
        }
    
        // Getter methods
        public String getBloodType() {
            return bloodType;
        }
    
        public ArrayList<String> getDiagnoses() {
            return diagnoses;
        }
    
        public ArrayList<String> getTreatments() {
            return treatments;
        }
    
        // Methods to add diagnosis and treatment (only accessible by Doctor)
        protected void addDiagnosis(String diagnosis) {
            diagnoses.add(diagnosis);
        }

        protected void addPrescription(String prescription){
            prescription.add(prescription);
        }


    protected void addTreatment(String treatment) {
        treatments.add(treatment);
    }

    public static void addMedicalRecord(String patientId, MedicalRecord record) {
        patientRecordMap.put(patientId, record);
    }

    // Static method to retrieve a medical record by patient ID
    public static MedicalRecord getMedicalRecordById(String patientId) {
        return patientRecordMap.get(patientId);
    }

    public static void displayRecords(String patientIdString) {
        patientRecordMap.get(patientIdString).viewMedicalRecord();
    }

    public static void updatePhoneNumber(String newPhoneNumber) {
    	phoneNumber = newPhoneNumber;
    }
    
    public static void updateEmailAddress(String newEmailAddress) {
    	emailAddress= newEmailAddress;
    }

    // Patients can view their medical record
    public void viewMedicalRecord() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Patient Name: " + patientName);
        System.out.println("Patient DOB: " + dob);
        System.out.println("Patient Gender: " + gender);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email Address: " + emailAddress);
        System.out.println("Blood Type: " + bloodType);
        System.out.println("Diagnoses: " + diagnoses);
        System.out.println("Treatments: " + treatments);
    }
}
