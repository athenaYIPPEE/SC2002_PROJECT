package labproject;

import java.util.Scanner;

public class DoctorMedicalRecord {

	private HashMap<String, Patient> patientsMap;
	
	public DoctorMedicalRecord() {
		this.patientsMap = new HashMap<>();
	}
	
	public void viewMedicalRecords() {
		if (patientsMap.isEmpty()) {
			System.out.println("No patients available.");
			return;
		}
		Scanner sc = new Scanner(System.in);
        	System.out.println("Enter the patient's ID: ");
        	String patientID = sc.next();
        
        	Patient patient = patientsMap.get(patientID);
        	if (patient != null) {
            		patient.getMedicalRecord(); //get
        	} else {
            		System.out.println("Patient not found.");
        	}
	}
	
	public void updateMedicalRecords() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the patient's ID: ");
        	String patientID = sc.next();
        
        	Patient patient = patientsMap.get(patientID);
        	if (patient == null) {
            		System.out.println("Patient not found.");
            		return;
        	}
        
        	boolean updating = true;
        	while (updating) {
            		System.out.println("What would you like to update?");
            		System.out.println("1. Add new diagnosis");
            		System.out.println("2. Add new prescription");
            		System.out.println("3. Add new treatment plan");
            		System.out.println("4. Exit update menu");
            		int choice = sc.nextInt();
            		sc.nextLine();
            
            		switch (choice) {
            		case 1:
                		System.out.println("Enter new diagnosis: ");
                		String diagnosis = sc.nextLine();
                		MedicalRecord.addDiagnosis(diagnosis); 
                		System.out.println("Diagnosis added.");
                		break;

            		case 2:
                		System.out.println("Enter new prescription: ");
                		String prescription = sc.nextLine();
                		MedicalRecord.addPrescription(prescription); 
                		System.out.println("Prescription added.");
                		break;

            		case 3:
                		System.out.println("Enter new treatment plan: ");
                		String treatmentPlan = sc.nextLine();
                		MedicalRecord.addTreatmentPlan(treatmentPlan); 
                		System.out.println("Treatment plan added.");
                		break;

            		case 4:
                		updating = false; // Exit the update menu
                		break;

            		default:
                		System.out.println("Invalid choice. Please try again.");
            		}
        	}
        
        	System.out.println("Medical record updated successfully.");

	}
	
}
