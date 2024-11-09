package project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientsAppointments {

    // Constructor

    public PatientsAppointments(String phoneNumber, String emailAddress) {

    }

    public void scheduleAppointment() {
        System.out.println("Select a Doctor: ");
        for (int i = 0; i < Doctor.doctors.size(); i++) {
            // Print the doctor number along with the doctor name
            System.out.println((i + 1) + ". " + Doctor.doctors.get(i));
        }
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt()-1;
        String chosenDoctor = Doctor.doctorNames.get(choose);
        AppointmentSlots.viewAppointmentSlots(chosenDoctor);
        Doctor selectedDoctor = Doctor.doctors.get(choose);
        AppointmentSlots.viewAppointmentSlots(selectedDoctor.getName());
        System.out.println("Enter a date (YYYY-MM-DD): ");
        String inputDate = scanner.nextLine();
        // Parse the input date into a LocalDate
        LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE);
        // Step 4: Print available time slots for the selected doctor on the chosen date
        AppointmentSlots appointmentSlots = selectedDoctor.getAppointmentSlots();
        List<LocalDateTime> availableSlots = new ArrayList<>();
        if (appointmentSlots != null) {
            availableSlots = appointmentSlots.getSlotsForDate(date);
            if (availableSlots.isEmpty()) {
                System.out.println("No available slots for this doctor on the selected date.");
                return;  // Exit if no slots are available
            }

            // Display available slots for the patient to choose from
            System.out.println("Available time slots for " + selectedDoctor.getName() + " on " + date + ":");
            for (int i = 0; i < availableSlots.size(); i++) {
                System.out.println((i + 1) + ". " + availableSlots.get(i).toLocalTime());
            }
        } else {
            System.out.println("No appointment slots are available for this doctor.");
            return;  // Exit if no slots are found
        }

        // Step 5: Let the patient select a time slot
        System.out.println("Select a time slot by entering the corresponding number:");
        int slotChoice = scanner.nextInt() - 1;  // Adjust for 0-based index

        // Validate the slot choice
        if (slotChoice >= 0 && slotChoice < availableSlots.size()) {
            LocalDateTime chosenSlot = availableSlots.get(slotChoice);
            System.out.println("You have selected " + chosenSlot.toLocalTime() + " for your appointment.");

            // Step 6: Schedule the appointment (create an Appointment object)
            Appointment newAppointment = new Appointment(appointmentID, selectedDoctor.getName(), patientId, appointmentDate, "Pending" chosenSlot);
            selectedDoctor.addAppointment(newAppointment);  // Add appointment to the doctor's list
            System.out.println("Appointment scheduled with Dr. " + selectedDoctor.getName() + " on " 
             + chosenSlot.toLocalDate() + " at " + chosenSlot.toLocalTime());
        } else {
            System.out.println("Invalid choice. Please try again.");
        
        }
    }   
    public void cancelAppointment(String appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                appointment.setStatus("Canceled");
                System.out.println("Appointment " + appointmentId + " has been canceled.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }

    public void rescheduleAppointment(String appointmentId, LocalDate newDate, String newTimeSlot) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                appointment.setAppointmentDate(newDate);
                appointment.setTimeSlot(newTimeSlot);
                System.out.println("Appointment " + appointmentId + " has been rescheduled.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }

    // Method to view all appointments for the patient
    @Override
    public void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment.toString());
            }
        }
    }
}
