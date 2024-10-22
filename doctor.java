package labproject;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class doctor extends all_users{

	private HashMap<String, Patient> patientsMap;
	private HashMap<String, String> diagnosisTreatmentMap;
	private List<Appointment> appointments; //appointment objects
	private List<String> slots;
	
	public doctor(String hospitalId, String password, String role) {
		super(hospitalId, password, role);
		this.patientsMap = new HashMap<>();
		this.diagnosisTreatmentMap = new HashMap<>();
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
			case 1: medicalRecordsView();
				break;
			case 2: medicalRecordsUpdate();
				break;
			case 3: personalSchedule();
				break;
			case 4: setAvailability();
				break;
			case 5: appointmentRequest();
				break;
			case 6: upcomingAppointmentsView();
				break;
			case 7: appointmentOutcome();
				break;
		}
	}
	
	public void medicalRecordsView() {
		if (patients.isEmpty()) {
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
        
		System.out.println("Enter new diagnosis: ");
		String newDiagnosis = sc.next();
		
		if (diagnosisTreatmentMap.containsKey(newDiagnosis)) {
            		String treatment = diagnosisTreatmentMap.get(newDiagnosis);
            		System.out.println("Treatment for diagnosis '" + newDiagnosis + "' is: " + treatment);
        	} else {
            		System.out.println("No treatment found for the diagnosis: " + newDiagnosis); //new method to add treatment plan?
        	}
	}
	
	public void personalSchedule() {
		System.out.println("Upcoming Appointments:");
        	if (appointments.isEmpty()) {
            		System.out.println("No upcoming appointments.");
        	} else {
            	for (Appointment appointment : appointments) {
                	System.out.println(appointment);  // Display each appointment
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
        	for (Appointment appointment : appointments) {
            		if (appointment.getStatus().equals("confirmed")) {
                	System.out.println(appointment);
            		}
        	}
    	}
	
    	public void appointmentOutcome() {
    		Scanner sc = new Scanner(System.in);
        	System.out.println("Enter the appointment ID to record the outcome: ");
        	String appointmentID = sc.nextLine();

        	for (Appointment appointment : appointments) {
            		if (appointment.getID().equals(appointmentID)) {
                		System.out.println("Enter the outcome of the appointment: ");
                		String outcome = sc.nextLine();
                		appointment.setOutcome(outcome);
                		System.out.println("Appointment outcome recorded.");
                		return;
            		}
        	}
        	System.out.println("Appointment not found.");
    		}
	}
