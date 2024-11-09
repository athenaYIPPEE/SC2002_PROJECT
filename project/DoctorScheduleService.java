package project;

import java.time.LocalDateTime;
import java.util.*;

public class DoctorScheduleService {

	private List<String> availableSlots;
	private Doctor doctor;
	
	public DoctorScheduleService(Doctor doctor) {
		this.doctor = doctor;
		this.availableSlots = new ArrayList<>();
	}
	
	public void viewPersonalSchedule() {
		System.out.println("Upcoming Appointments:");
        if (doctor.getAppointments().isEmpty()) {  // Correctly using doctor instance
            System.out.println("No upcoming appointments.");
        } else {
            for (Appointment appointment : doctor.getAppointments()) {  // Accessing the doctor's appointments list
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
            doctor.appointmentSlots.add(slot);
        }
        System.out.println("Slots added successfully for Dr. " + Doctor.getName());
    }
	
	}

	
}
