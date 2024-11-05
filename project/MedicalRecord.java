package project;

import java.util.ArrayList;

public class MedicalRecord {
    private String bloodType;
    private ArrayList<String> diagnoses;
    private ArrayList<String> treatments;

    // Constructor
    public MedicalRecord(String bloodType) {
        this.bloodType = bloodType;
        this.diagnoses = new ArrayList<>();
        this.treatments = new ArrayList<>();
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

    protected void addTreatment(String treatment) {
        treatments.add(treatment);
    }

    // Patients can view their medical record
    public void viewMedicalRecord() {
        System.out.println("Blood Type: " + bloodType);
        System.out.println("Diagnoses: " + diagnoses);
        System.out.println("Treatments: " + treatments);
    }
}
