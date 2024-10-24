package labproject;

import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends all_users{

	private HashMap<String, Patient> patientsMap;
	private List<Appointment> appointments; //appointment objects
	private List<String> slots;
	
	public doctor(String hospitalId, String password, String role) {
		super(hospitalId, password, role);
		this.patientsMap = new HashMap<>();
		this.appointments = new ArrayList<>();
        	this.slots = new ArrayList<>();
	}
	
	public void displaymenu() {
		System.out.println("Menu: \n"
				+ "1: View Patient Medical Records \n"
				+ "2: Update Patient Medical Records \n"
				+ "3: View Personal Schedule \n"
				+ "4: Set Availability for Appointments \n"
				+ "5: Accept or Decline Appointment Requests \n"
				+ "6: View Upcoming Appointments \n"
				+ "7: Record Appointment Outcome");
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();
		switch(option) {
			case 1: medicalRecordsView(); break;
			case 2: medicalRecordsUpdate(); break;
			case 3: personalSchedule(); break;
			case 4: setAvailability(); break;
			case 5: appointmentRequest(); break;
			case 6: upcomingAppointmentsView(); break;
			case 7: appointmentOutcome(); break;
			default: System.out.println("Invalid option. Please try again."); break;	
		}
	}
	
	public void medicalRecordsView() {
		if (patientsMap.isEmpty()) {
			System.out.println("No patients available");
			return;
		}
		Scanner sc = new Scanner(System.in);
        	System.out.println("Enter the patient's ID: ");
        	String patientID = sc.next();
        
		for (Patient patient : patients) {
			if (patient.getID().equals(patientID)) {  
	            		patient.viewMedicalRecord(); 
	            		return; // exit once the patient's record is displayed
	        	}
		}
		System.out.println("Patient not found.");
	}
	
	public void medicalRecordsUpdate() {
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
                			medicalRecord.addTreatmentPlan(treatmentPlan); 
                			System.out.println("Treatment plan added.");
                			break;
				case 4: 	
					updating = false; // Exit the update menu
                			break;
				default:
                			System.out.println("Invalid choice. Please try again.");
            		}
		} System.out.println("Medical record updated successfully.");
	}
	
	public void personalSchedule() {
		System.out.println("Upcoming Appointments:");
        	if (appointments.isEmpty()) {
            		System.out.println("No upcoming appointments.");
        	} else {
            	for (Appointment appointment : appointments) {
                	System.out.println(appointment);  // Display each appointment
            	}
		}	
        	System.out.println("Available Slots: ");
        	if (slots.isEmpty()) {System.out.println("No available slots.");}
        	else {
        		for (String avail: slots) {
        			System.out.println(slots);
        		}
        	}
	}
        
    	public void setAvailability() {
    		Scanner sc = new Scanner(System.in);
        	System.out.println("Enter your availability slot (e.g., 'Monday 9-10 AM'):");
        	String slot = sc.nextLine();
        	slots.add(slot);
        	System.out.println("Availability slot added successfully.");
    	}
    
    	public void appointmentRequest() {
    		Scanner sc = new Scanner(System.in);
        	System.out.println("Enter the appointment ID to review: ");
        	String appointmentID = sc.nextLine();

        	for (Appointment appointment : appointments) {
            		if (appointment.getID().equals(appointmentID)) {
                		System.out.println("Do you want to accept or decline this appointment? (Enter 'accept' or 'decline')");
                		String decision = sc.nextLine();
                		if (decision.equalsIgnoreCase("accept")) {
                    			appointment.setStatus("confirmed");
                    			System.out.println("Appointment confirmed.");
                		} else if (decision.equalsIgnoreCase("decline")) {
                    			appointment.setStatus("cancelled");
                    			System.out.println("Appointment declined.");
                		} else {
                    			System.out.println("Invalid choice.");
                		}
                		return;
            		}
        	}
        	System.out.println("Appointment not found.");
    	}
    
    	public void upcomingAppointmentsView() {
    		System.out.println("Upcoming confirmed appointments:");
		boolean hasConfirmedAppointments = false;
        	for (Appointment appointment : appointments) {
            		if (appointment.getStatus().equals("confirmed")) {
                		System.out.println(appointment);
            		}
        	}
		if (!hasConfirmedAppointments) {
			System.out.println("No confirmed appointments.");
		}
    	}
	
    	public void appointmentOutcome() {
    		Scanner sc = new Scanner(System.in);
        	System.out.println("Enter the appointment ID to record the outcome: ");
        	String appointmentID = sc.nextLine();

        	for (Appointment appointment : appointments) {
            		if (appointment.getID().equals(appointmentID)) {
                		//record date
            			LocalDate appointmentDate = LocalDate.now();
            			System.out.println("Date of Appointment: " + appointmentDate);
            			appointment.setDate(appointmentDate);

				//serviceType
				System.out.println("Select type of service provided:");
				for (int i = 0; i < serviceType.values().length; i++) {
					System.out.println((i + 1) + ": " + serviceType.values()[i]);
				}
				int serviceChoice = sc.nextInt() - 1;
				sc.nextLine();
				if (serviceChoice >= 0 && serviceChoice < serviceType.values().length) {
					serviceType selectedService = serviceType.values()[serviceChoice];
					appointment.setServiceType(selectedService);
					System.out.println("Service type recorded: " + selectedService);
				} else { 
					System.out.println("Invalid service type selected.");
				}
                		
				//medication 
				boolean addingMedications = true;
				while (addingMedications) {
					System.out.print("Enter medication name (or type 'exit' to stop adding medications): ");
					String medicationName = sc.nextLine();
					if (medicationName.equalsIgnoreCase("exit")) {
						addingMedications = false;
						continue;
					}
					Medication medication = new Medication(medicationName, 0);
					appointment.addMedication(medication);
					
					System.out.println("Medication added: " + medicationName); 
				}

				//consultation
				boolean addingNotes = true;
				while (addingNotes) {
					System.out.println("Enter consultation note (or type 'exit' to stop adding notes): ");
					String consultationNote = sc.nextLine();
					appointment.recordConsultationNotes(consultationNote);
					if (consultationNote.equalsIgnoreCase("exit")) {
						addingNotes = false;
						continue;
					}
				} System.out.println("Appointment outcome recorded.");
			}	
            		else System.out.println("Appointment not found.");
        	}
        	
    	}
}
