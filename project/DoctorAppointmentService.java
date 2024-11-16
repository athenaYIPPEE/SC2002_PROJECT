package project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DoctorAppointmentService {
	
	private Doctor doctor;
	private static HashMap<LocalDate, AppointmentSlots> personalSchedule;
	
	public DoctorAppointmentService(Doctor doctor) {
		this.doctor = doctor;
		this.personalSchedule = doctor.personalSchedule;
	}
	
	public void appointmentRequest() {
		Scanner sc = new Scanner(System.in);
        for (Appointment appointment : doctor.appointments) {
            if (appointment.getStatus().equals("Pending")) {
				System.out.println(appointment.toString());
                System.out.println("Do you want to accept or decline this appointment? (1. Accept 2.Decline)");
                int decision = sc.nextInt();
                if (decision == 1) {
                    appointment.setStatus("Confirmed");
                    System.out.println("Appointment confirmed.");
					LocalDateTime date = appointment.getAppointmentDate();
					LocalDate date1 = date.toLocalDate();
					AppointmentSlots slotList = personalSchedule.get(date1);
					if (slotList != null) {
						// Remove the specific slot from the list
						slotList.slots.remove(date);
					
						// If no slots are left, remove the date from personalSchedule
						if (slotList.slots.isEmpty()) {
							personalSchedule.remove(date1);
						}
					}
                } else if (decision == 2) {
                    appointment.setStatus("Cancelled");
                    System.out.println("Appointment declined.");
                } else {
                    System.out.println("Invalid choice.");
                }
            }
        }
	}
	
	public void viewUpcomingAppointments() {
		System.out.println("Upcoming confirmed appointments:");
    	boolean hasConfirmedAppointments = false;
    	for (Appointment appointment : doctor.appointments) {
    		if (appointment.getStatus().equals("Confirmed")) {
            	System.out.println(appointment);
                hasConfirmedAppointments = true;
            	}
        	}
        	if (hasConfirmedAppointments == false) {
				System.out.println("No confirmed appointments.");
        	}
	}
	
	public void recordAppointmentOutcome() {
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter the appointment ID to record the outcome: ");
    	String appointmentID = sc.nextLine();
		boolean found = false;

        for (Appointment appointment : doctor.appointments) {
            if (appointment.getAppointmentId().equals(appointmentID)) {
				//record date
				found = true;
				LocalDateTime appointmentDate = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				String formattedDate = appointmentDate.format(formatter);
				System.out.println(formattedDate);
				appointment.setAppointmentDateTime(appointmentDate);
            	
				//service type
				System.out.println("Select type of service provided:");
				for (int i = 0; i < ServiceType.values().length; i++) {
					System.out.println((i + 1) + ": " + ServiceType.values()[i]);
				}
				int serviceChoice = sc.nextInt() - 1; // Get the user choice, adjusting for 0-index
				sc.nextLine();
				if (serviceChoice >= 0 && serviceChoice < ServiceType.values().length) {
					ServiceType selectedService = ServiceType.values()[serviceChoice];
					String serviceTypeString = selectedService.name();
					appointment.setServiceType(serviceTypeString); // ADD
					System.out.println("Service type recorded: " + selectedService);
				} else {
						System.out.println("Invalid service type selected.");
				}
                
				//medication
				boolean addingMedications = true;
				while (addingMedications) {
				System.out.println("Medication prescribed: ");
				for (int i = 0; i < MedicationName.values().length; i++) {
					System.out.println((i + 1) + ": " + MedicationName.values()[i].getName());
				} 
					System.out.println((MedicationName.values().length + 1) + ": No medication needed");
					Scanner scanner = new Scanner(System.in);

					int choice = scanner.nextInt();
					if (choice < 1 || choice > MedicationName.values().length+1) {
						System.out.println("Invalid choice. Please select a valid medication.");
						continue; // Restart the loop if the input is invalid
					}
					if (choice == MedicationName.values().length + 1) {
						break;  
					}
					MedicationName medication = MedicationName.values()[choice - 1];
					Medication prescribedMedication = new Medication(medication);
					appointment.addMedication(prescribedMedication); // Add the medication to the appointment

					System.out.println("Medication added: " + medication);    
					System.out.println("Do you want to add another medication? (y/n)");
					String response = scanner.next();
					addingMedications = response.equalsIgnoreCase("y");
				}
					
				//consultation
				boolean addingNotes = true;
				while (addingNotes) {
					System.out.println("Enter consultation note (or type 'exit' to stop adding notes): ");
					String consultationNote = sc.nextLine();
					appointment.recordConsultationNotes(consultationNote);
					if (consultationNote.equalsIgnoreCase("exit")) {
						addingNotes = false;
					}
				} 
				appointment.setStatus("Completed");
				System.out.println("Appointment outcome recorded and completed.");

            } 
            
        }if (found == false) System.out.println("Appointment not found.");
	}
	
}
