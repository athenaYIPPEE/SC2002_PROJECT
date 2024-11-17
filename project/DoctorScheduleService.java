package project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class DoctorScheduleService {

	private Doctor doctor;
	
	public DoctorScheduleService(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public void viewPersonalSchedule() {
		System.out.println("Appointments:");
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

            //test
            LocalDate datePart = slot.toLocalDate();
            doctor.setAvailability(datePart, doctor.appointmentSlots);
            //test
        }

        System.out.println("Slots added successfully for Dr. " + doctor.getName());

        System.out.println("Appointment Slots:");
        for (LocalDateTime slot : doctor.appointmentSlots) {
            System.out.println(slot); // This will print each LocalDateTime in the default format
        } //testing
    }
	
}

	

