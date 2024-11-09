package project;

import java.time.LocalDateTime;
import java.util.*;

public class DoctorScheduleService {

	private List<String> availableSlots;
	
	public DoctorScheduleService() {
		this.availableSlots = new ArrayList<>();
	}
	
	public void viewPersonalSchedule() {
		System.out.println("Upcoming Appointments:");
        	if (appointments.isEmpty()) {
            		System.out.println("No upcoming appointments.");
        	} else {
            		for (Appointment appointment : appointments) {
                	System.out.println(appointment);  // Display each appointment
            		}
        	}
	}
	
	public void setAvailability() {
		Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of available slots you want to add:");
        int numberOfSlots = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        for (int i = 0; i < numberOfSlots; i++) {
            System.out.println("Enter slot " + (i + 1) + " in the format (YYYY-MM-DDTHH:MM): ");
            String slotString = scanner.nextLine();
            LocalDateTime slot = LocalDateTime.parse(slotString);
            Doctor.appointmentSlots.add(slot);
        }
        System.out.println("Slots added successfully for Dr. " + Doctor.getName());
    }
	
	}

	
}
