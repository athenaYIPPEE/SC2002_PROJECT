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
                    System.out.println("Appointment " +appointmentID+  " requested with Dr. " + selectedDoctor.getName() + " on " 
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
                appointment.setStatus("Cancelled");
                LocalDateTime date = appointment.getAppointmentDate();
				LocalDate date1 = date.toLocalDate();
				int i;
				for (i = 0; i < Doctor.doctorNames.size(); i++) {
	                if (Doctor.doctorNames.get(i).equals(appointment.getDoctorName())){
	                	break;
	                }
				}
	            Doctor doctor = Doctor.doctors.get(i);    
				AppointmentSlots slotList = doctor.personalSchedule.get(date1);
				if (slotList == null) {
				    // If the date does not exist, create a new AppointmentSlots object
				    slotList = new AppointmentSlots(appointment.getDoctorName()); // Replace with actual doctor name
				    doctor.personalSchedule.put(date1, slotList);  // Add the new date and its AppointmentSlots object to the personal schedule
				}
				slotList.addSlot(date);
                System.out.println("Appointment " + appointmentId + " has been cancelled.");
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
                if (appointment.getStatus().equals("Confirmed")) {
                	appointmentToReschedule = appointment;
                    break;
                }else {
                	System.out.println("No confirmed appointments to reschedule.");
                	return;
                }
            }
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
        LocalDateTime date = appointmentToReschedule.getAppointmentDate();
    	LocalDate date1 = date.toLocalDate();
    	int j;
    	for (j = 0; j < Doctor.doctorNames.size(); j++) {
    		if (Doctor.doctorNames.get(j).equals(appointmentToReschedule.getDoctorName())){
    		break;
    		}
    	}
    	Doctor doctor = Doctor.doctors.get(j);    
    	AppointmentSlots slotList = doctor.personalSchedule.get(date1);
    	if (slotList == null) {
    	    slotList = new AppointmentSlots(appointmentToReschedule.getDoctorName()); // Replace with actual doctor name
    	    doctor.personalSchedule.put(date1, slotList);  // Add the new date and its AppointmentSlots object to the personal schedule
    	}
    	slotList.addSlot(date); 
        LocalDateTime newTimeSlot = slots.get(slotChoice);
        
        LocalDateTime newAppointmentDateTime = LocalDateTime.of(newDate, newTimeSlot.toLocalTime());
    
        // Confirm the new appointment details
        System.out.println("You have selected " + newAppointmentDateTime + " for your appointment.");
        
        // Reschedule the appointment by updating the appointment's date and time
        appointmentToReschedule.setAppointmentDateTime(newAppointmentDateTime);  // Update with LocalDateTime
        appointmentToReschedule.setStatus("Pending");
        
        System.out.println("Appointment " + appointmentId + " reschedule to Dr. " + selectedDoctor.getName() + " on " 
                        + newAppointmentDateTime.toLocalDate() + " at " + newAppointmentDateTime.toLocalTime() + " has been requested");
    
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
        boolean found = false;
        for (Appointment appointment : currentPatient.getAppointments()) {
            // Check if the status of the appointment is "Completed"
            if (appointment.getStatus().equals("Completed")) {
                found = true;
                appointment.getOutcome(appointment);
            }
        } if (found == false) System.out.println("No past appointments.");
    }
}   
