package labproject;

import java.time.LocalDate;
import java.util.Scanner;

public class doctorAppointmentService {

	private List<Appointment> appointments;
	
	public doctorAppointmentService() {
		this.appointments = new ArrayList<>();
	}
	
	public void appointmentRequest() {
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter the appointment ID to review: ");
        String appointmentID = sc.nextLine();

        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentID)) {
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
	
	public void viewUpcomingAppointments() {
		System.out.println("Upcoming confirmed appointments:");
    	boolean hasConfirmedAppointments = false;
        for (Appointment appointment : appointments) {
            if (appointment.getStatus().equals("confirmed")) {
                System.out.println(appointment);
                hasConfirmedAppointments = true;
            }
        }
        if (!hasConfirmedAppointments) {
            System.out.println("No confirmed appointments.");
        }
	}
	
	public void recordAppointmentOutcome() {
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter the appointment ID to record the outcome: ");
        String appointmentID = sc.nextLine();

        for (Appointment appointment : appointments) {
            if (appointment.getID().equals(appointmentID)) {
                //record date
            	LocalDate appointmentDate = LocalDate.now();
            	System.out.println(appointmentDate);
            	appointment.setDate(appointmentDate);
            	
            	//service type
            	System.out.println("Select type of service provided:");
            	for (int i = 0; i < serviceType.values().length; i++) {
                    System.out.println((i + 1) + ": " + serviceType.values()[i]);
                }
                int serviceChoice = sc.nextInt() - 1; // Get the user choice, adjusting for 0-index
                sc.nextLine();
                if (serviceChoice >= 0 && serviceChoice < serviceType.values().length) {
                    serviceType selectedService = serviceType.values()[serviceChoice];
                    appointment.setServiceType(selectedService); // ADD
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
                    Medication medication = new Medication(medicationName, 0); // Default stock to 0?
                    appointment.addMedication(medication); // Add the medication to the appointment

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