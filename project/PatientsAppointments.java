package project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientsAppointments {

    // Constructor
    private static Patients currentPatient;
    
        public PatientsAppointments(Patients patient) {
            this.currentPatient = patient;
        }
    
        public static void scheduleAppointment() {
            System.out.println("Select a Doctor: ");
            for (int i = 0; i < Doctor.doctors.size(); i++) {
                // Print the doctor number along with the doctor name
                System.out.println((i + 1) + ". " + Doctor.doctors.get(i));
            }
            Scanner scanner = new Scanner(System.in);
            int choose = scanner.nextInt()-1;
            
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
                String appointmentID;
                boolean isUnique;
                do {
                    appointmentID = "A" + (int)(Math.random() * 1000);
                    isUnique = true;
                    
                    // Check if this ID already exists in any appointment in Doctor.appointments
                    for (Appointment appointment : selectedDoctor.getAppointments()) { // Assuming getAppointments() exists
                        if (appointment.getAppointmentId().equals(appointmentID)) {
                            isUnique = false;
                            break; // Exit loop if ID is not unique
                        }
                    }
                } while (!isUnique);
                String patientId = currentPatient.getPatientId();
            // service type
            System.out.println("Select type of service provided:");
               for (int i = 0; i < serviceType.values().length; i++) {
                       System.out.println((i + 1) + ": " + serviceType.values()[i]);
                  }
                int serviceChoice = scanner.nextInt() - 1;
                if (serviceChoice >= 0 && serviceChoice < serviceType.values().length) {
                    serviceType selectedService = serviceType.values()[serviceChoice];
            String serviceTypeString = selectedService.name();
            Appointment newAppointment = new Appointment(appointmentID, selectedDoctor.getName(), patientId, chosenSlot, "Pending", serviceTypeString);
            selectedDoctor.addAppointment(newAppointment);  // Add appointment to the doctor's list
            currentPatient.addAppointment(newAppointment);
            System.out.println("Appointment scheduled with Dr. " + selectedDoctor.getName() + " on " 
             + chosenSlot.toLocalDate() + " at " + chosenSlot.toLocalTime());
        } else {
            System.out.println("Invalid choice. Please try again.");
        
        }
    }
}   
    public static void cancelAppointment(String appointmentId) {
        // Find the appointment by ID in the current patient's appointment list
        for (Appointment appointment : currentPatient.getAppointments()) { // Get appointments from the current patient
            if (appointment.getAppointmentId().equals(appointmentId)) {
                // Set the status to "Canceled"
                appointment.setStatus("Canceled");
            
                System.out.println("Appointment " + appointmentId + " has been canceled.");
                return;
            }
        }
        // If no appointment with the provided ID is found
        System.out.println("Appointment not found.");
    }


    public static void rescheduleAppointment(String appointmentId) {
        // Find the appointment by ID in the current patient's appointment list
        Appointment appointmentToReschedule = null;
        for (Appointment appointment : currentPatient.getAppointments()) { 
            if (appointment.getAppointmentId().equals(appointmentId)) {
                appointmentToReschedule = appointment;
                break;
            }
        }
        
        if (appointmentToReschedule == null) {
            System.out.println("Appointment not found.");
            return;  // Exit if the appointment is not found
        }
    
        // Let the patient select a doctor to reschedule with
        System.out.println("Select a Doctor: ");
        for (int i = 0; i < Doctor.doctors.size(); i++) {
            System.out.println((i + 1) + ". " + Doctor.doctors.get(i).getName());
        }
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt() - 1;  // 0-based index
        if (choose < 0 || choose >= Doctor.doctors.size()) {
            System.out.println("Invalid doctor selection.");
            return;  // Exit if the selection is invalid
        }
        
        Doctor selectedDoctor = Doctor.doctors.get(choose);
        // Assuming that the AppointmentSlots class has a method to view available slots for a doctor
        AppointmentSlots.viewAppointmentSlots(selectedDoctor.getName());  
        
        // Get a new date for the appointment
        System.out.println("Enter a new date (YYYY-MM-DD): ");
        scanner.nextLine();  // Consume the newline character
        String inputDate = scanner.nextLine();
        LocalDate newDate;
        try {
            newDate = LocalDate.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            System.out.println("Invalid date format.");
            return;  // Exit if the date is invalid
        }
    
        // Get the available slots for the new date
        AppointmentSlots appointmentSlots = selectedDoctor.getAppointmentSlots();
        List<LocalDateTime> availableSlots = appointmentSlots.getSlotsForDate(newDate);
        if (availableSlots.isEmpty()) {
            System.out.println("No available slots for the selected date.");
            return;  // Exit if no slots are available
        }
    
        // Display available slots for the patient to choose from
        System.out.println("Available time slots for Dr. " + selectedDoctor.getName() + " on " + newDate + ":");
        for (int i = 0; i < availableSlots.size(); i++) {
            System.out.println((i + 1) + ". " + availableSlots.get(i).toLocalTime());
        }
    
        // Let the patient select a time slot
        System.out.println("Select a time slot by entering the corresponding number:");
        int slotChoice = scanner.nextInt() - 1;  // Adjust for 0-based index
        if (slotChoice < 0 || slotChoice >= availableSlots.size()) {
            System.out.println("Invalid slot choice.");
            return;  // Exit if the choice is invalid
        }
        
        LocalDateTime newTimeSlot = availableSlots.get(slotChoice);
        
        LocalDateTime newAppointmentDateTime = LocalDateTime.of(newDate, newTimeSlot.toLocalTime());
    
    // Confirm the new appointment details
    System.out.println("You have selected " + newAppointmentDateTime + " for your appointment.");
    
    // Reschedule the appointment by updating the appointment's date and time
    appointmentToReschedule.setAppointmentDateTime(newAppointmentDateTime);  // Update with LocalDateTime
    
    System.out.println("Appointment " + appointmentId + " has been rescheduled with Dr. " + selectedDoctor.getName() + " on " 
                       + newAppointmentDateTime.toLocalDate() + " at " + newAppointmentDateTime.toLocalTime());
    }

    public static void viewScheduledAppointments(String appointmentId) {
        // Assuming currentPatient.getAppointments() returns a list of appointments
        for (Appointment appointment : currentPatient.getAppointments()) {
            // Check if the status of the appointment is "Confirmed"
            if (appointment.getStatus().equals("Confirmed")) {
                // Print the appointment details (you may need to use toString() or customize the print output)
                System.out.println(appointment);  // Assuming toString() method in Appointment handles the print
            }
        }
    }
    
    public static void viewPastRecords(String id) {
        for (Appointment appointment : currentPatient.getAppointments()) {
            // Check if the status of the appointment is "Completed"
            if (appointment.getStatus().equals("Completed")) {
                // Call getOutcome() on the instance of Appointment
                appointment.getOutcome();
            }
        }
    }
    
    

}