package project;

import java.util.*;

public class DoctorMedicalRecord {

	private HashMap<String, Patients> patientsMap;
	private HashMap<String, MedicalRecord> doctorRecord;
	private Doctor doctor;
	
	public DoctorMedicalRecord(Doctor doctor) {
		this.doctor = doctor;
		this.patientsMap = Patients.patientList;
		this.doctorRecord = MedicalRecord.patientRecordMap;
	}
	
	public void viewMedicalRecords() {
		if (patientsMap.isEmpty()) {
			System.out.println("No patients available.");
			return;
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the patient's ID: ");
		String patientID = sc.next();
	
		Patients patient = patientsMap.get(patientID);
		MedicalRecord medicalRecord = doctorRecord.get(patient.getHospitalId());
		if (patient != null) {
			medicalRecord.displayRecords(patientID);
		} 
	}
	
	public void updateMedicalRecords() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the patient's ID: ");
		String patientID = sc.next();
	
		Patients patient = patientsMap.get(patientID);
		MedicalRecord medicalRecord = doctorRecord.get(patient.getHospitalId());
	
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
					medicalRecord.addDiagnosis(diagnosis);
					System.out.println("Diagnosis added.");
					break;
				case 2:
					System.out.println("Enter new prescription: ");
					String prescription = sc.nextLine();
					medicalRecord.addPrescription(prescription); 
					System.out.println("Prescription added.");
					break;
				case 3:
					System.out.println("Enter new treatment plan: ");
					String treatmentPlan = sc.nextLine();
					medicalRecord.addTreatment(treatmentPlan); 
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
