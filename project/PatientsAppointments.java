package project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PatientsAppointments {

    // Constructor
    private Patients currentPatient;
    
        public PatientsAppointments(Patients patient) {
            this.currentPatient = patient;
        }
    
        public void scheduleAppointment() {
            System.out.println("Select a Doctor: ");
            for (int i = 0; i < Doctor.doctorNames.size(); i++) {
                // Print the doctor number along with the doctor name
                System.out.println((i + 1) + ". Dr." + Doctor.doctorNames.get(i));
            }
            Scanner scanner = new Scanner(System.in);
            int choose = scanner.nextInt()-1;
            String buffer = scanner.nextLine();
            
            Doctor selectedDoctor = Doctor.doctors.get(choose);
            boolean hasAppointment = selectedDoctor.getAppointmentSlots(selectedDoctor.getName()).viewAppointmentSlotsBoolean(selectedDoctor.getName());

            if (hasAppointment == false) return;
    
            System.out.println("Enter a date (YYYY-MM-DD): ");
            String inputDate = scanner.nextLine();
            // Parse the input date into a LocalDate
            LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE);
    
            // Step 4: Print available time slots for the selected doctor on the chosen date
            HashMap<LocalDate, AppointmentSlots> appointmentSlots = selectedDoctor.returnPersonalSchedule();
            AppointmentSlots availableSlots = appointmentSlots.get(date);
            if (availableSlots == null) {
                System.out.println("No available time slots for " + selectedDoctor.getName() + " on " + date + ".");
                return;  // Exit the method if no slots are available
            }
            List<LocalDateTime> slots = availableSlots.getSlotsForDate(date);
            // Display available slots for the patient to choose from
            System.out.println("Available time slots for " + selectedDoctor.getName() + " on " + date + ":");
            for (int i = 0; i < slots.size(); i++) {
                System.out.println((i + 1) + ". " + slots.get(i).toLocalTime());
            }

            // Step 5: Let the patient select a time slot
            System.out.println("Select a time slot by entering the corresponding number:");
            int slotChoice = scanner.nextInt() - 1;  // Adjust for 0-based index
    
            // Validate the slot choice
            if (slotChoice >= 0 && slotChoice < slots.size()) {
                LocalDateTime chosenSlot = slots.get(slotChoice);
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
               for (int i = 0; i < ServiceType.values().length; i++) {
                       System.out.println((i + 1) + ": " + ServiceType.values()[i]);
                  }
                int serviceChoice = scanner.nextInt() - 1;
                if (serviceChoice >= 0 && serviceChoice < ServiceType.values().length) {
                    ServiceType selectedService = ServiceType.values()[serviceChoice];
            String serviceTypeString = selectedService.name();
            Appointment newAppointment = new Appointment(appointmentID, selectedDoctor.getName(), patientId, chosenSlot, "Pending", serviceTypeString);
            selectedDoctor.addAppointment(newAppointment);  // Add appointment to the doctor's list
            currentPatient.addAppointment(newAppointment);
            System.out.println("Appointment requested with Dr. " + selectedDoctor.getName() + " on " 
             + chosenSlot.toLocalDate() + " at " + chosenSlot.toLocalTime());
        } else {
            System.out.println("Invalid choice. Please try again.");
        
        }
    }
}   
    public void cancelAppointment(String appointmentId) {
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


    public void rescheduleAppointment(String appointmentId) {
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
        for (Appointment appointment : selectedDoctor.getAppointments()){
            if (!appointment.getStatus().equals("Confirmed"))
                System.out.println(appointment);
        }
        
        
        // Get a new date for the appointment
        System.out.println("Enter a new date (YYYY-MM-DD): ");
        scanner.nextLine();  // Consume the newline character
        String inputDate = scanner.nextLine();
        LocalDate newDate;
        newDate = LocalDate.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE);
    
        // Get the available slots for the new date
        HashMap<LocalDate, AppointmentSlots> appointmentSlots = selectedDoctor.returnPersonalSchedule();
            //List<AppointmentSlots> availableSlots = new ArrayList<>();
            AppointmentSlots availableSlots = appointmentSlots.get(newDate);
            List<LocalDateTime> slots = availableSlots.getSlotsForDate(newDate);
                //availableSlots = appointmentSlots.getSlotsForDate(date);
                /*if (availableSlots == null) {
                    System.out.println("No available slots for this doctor on the selected date.");
                    return;  // Exit if no slots are available
                }
                */
                // Display available slots for the patient to choose from
                System.out.println("Available time slots for " + selectedDoctor.getName() + " on " + newDate + ":");
                for (int i = 0; i < slots.size(); i++) {
                    System.out.println((i + 1) + ". " + slots.get(i).toLocalTime());
                }
    
        // Let the patient select a time slot
        System.out.println("Select a time slot by entering the corresponding number:");
        int slotChoice = scanner.nextInt() - 1;  // Adjust for 0-based index
        if (slotChoice < 0 || slotChoice >= slots.size()) {
            System.out.println("Invalid slot choice.");
            return;  // Exit if the choice is invalid
        }
        
        LocalDateTime newTimeSlot = slots.get(slotChoice);
        
        LocalDateTime newAppointmentDateTime = LocalDateTime.of(newDate, newTimeSlot.toLocalTime());
    
    // Confirm the new appointment details
    System.out.println("You have selected " + newAppointmentDateTime + " for your appointment.");
    
    // Reschedule the appointment by updating the appointment's date and time
    appointmentToReschedule.setAppointmentDateTime(newAppointmentDateTime);  // Update with LocalDateTime
    appointmentToReschedule.setStatus("Pending");
    
    System.out.println("Appointment " + appointmentId + " has been rescheduled with Dr. " + selectedDoctor.getName() + " on " 
                       + newAppointmentDateTime.toLocalDate() + " at " + newAppointmentDateTime.toLocalTime());
    }

    public void viewScheduledAppointments() {
        // Assuming currentPatient.getAppointments() returns a list of appointments
        for (Appointment appointment : currentPatient.getAppointments()) {
            // Check if the status of the appointment is "Confirmed"
                // Print the appointment details (you may need to use toString() or customize the print output)
                System.out.println(appointment); // Assuming toString() method in Appointment handles the print
            }
        }
    
    public void viewPastRecords() {
        for (Appointment appointment : currentPatient.getAppointments()) {
            // Check if the status of the appointment is "Completed"
            if (appointment.getStatus().equals("Completed")) {
                // Call getOutcome() on the instance of Appointment
                appointment.getOutcome(appointment);
            }
        }
    }
    
    

}   