// hashmap of appointment slots
// dr needs access --

package project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class AppointmentSlots {
    protected List<LocalDateTime> slots;
    private String doctorName;  

    
        public AppointmentSlots(String doctorName){
            this.doctorName = doctorName;
            this.slots = new ArrayList<>();
        }

        // Method to add a slot
        public void addSlot(LocalDateTime slot) {
            this.slots.add(slot);
        }


        public List<LocalDateTime> getSlots() {
            return this.slots;
        }

        public List<LocalDateTime> getSlotsForDate(LocalDate date) {
            List<LocalDateTime> slotsForDate = new ArrayList<>();
            for (LocalDateTime slot : slots) {
                if (slot.toLocalDate().isEqual(date)) {
                    slotsForDate.add(slot);
                }
            }
            return slotsForDate;
        }
    
        public static void viewAppointmentSlots(String doctorName) {
            boolean found = false;
            boolean hasSlots = false;
            for (Doctor doctor : Doctor.doctors) {
                if (doctorName.equals(doctor.getName())) {
                    found = true;
                    
                    //AppointmentSlots appointmentSlots = doctor.getAppointmentSlots(); // Get this doctor's AppointmentSlots
                    HashMap<LocalDate, AppointmentSlots> appointmentSlots = doctor.returnPersonalSchedule();

                    System.out.println("Available Appointment Slots for Dr. " + doctorName + ": ");
                    
                    for (Map.Entry<LocalDate, AppointmentSlots> entry : appointmentSlots.entrySet()) {
                        LocalDate date = entry.getKey(); // The date (key)
                        AppointmentSlots slotsForDate = entry.getValue(); // The AppointmentSlots for that date (value)
                
                // Print the date and available slots for that date
                List<LocalDateTime> slots = slotsForDate.getSlotsForDate(date);

                // Only print if there are available slots
                if (!slots.isEmpty()) {
                    hasSlots = true;
                    System.out.println("Date: " + date);
                    for (LocalDateTime slot : slots) {
                        System.out.println("  " + slot.toLocalTime()); // Print each slot for the date
                    }
                }
            }
            if (!hasSlots) {
                System.out.println("No available slots for Dr. " + doctorName + ".");
            }
                }
            }
            if (found == false) System.out.println("Doctor not found.");
        }

        public static boolean viewAppointmentSlotsBoolean(String doctorName) {
            boolean hasSlots = false;  // Track if there are any available slots
        
            for (Doctor doctor : Doctor.doctors) {
                if (doctorName.equals(doctor.getName())) {
                    HashMap<LocalDate, AppointmentSlots> appointmentSlots = doctor.returnPersonalSchedule();
        
                    System.out.println("Available Appointment Slots for Dr. " + doctorName + ": ");
                    
                    // Check if there are any available slots for the doctor
                    for (Map.Entry<LocalDate, AppointmentSlots> entry : appointmentSlots.entrySet()) {
                        LocalDate date = entry.getKey();
                        AppointmentSlots slotsForDate = entry.getValue();
                        List<LocalDateTime> slots = slotsForDate.getSlotsForDate(date);
        
                        if (!slots.isEmpty()) {
                            hasSlots = true;
                            System.out.println("Date: " + date);
                            for (LocalDateTime slot : slots) {
                                System.out.println("  " + slot.toLocalTime()); // Print each slot for the date
                            }
                        }
                    }
        
                    if (!hasSlots) {
                        System.out.println("No available slots for Dr. " + doctorName + ".");
                    }
        
                    return hasSlots;  // Return whether there are available slots
                }
            }
        
            System.out.println("Doctor not found.");  // If no doctor is found
            return false;  // Return false if the doctor is not found
        }

        public void printTimeSlotsForDate(LocalDateTime date) {
            boolean found = false;
    
            for (LocalDateTime slot : slots) {
                // Compare the date part of LocalDateTime with the input date
                if (slot.toLocalDate().isEqual(date.toLocalDate())) {
                    // Print the time (hours and minutes) part of LocalDateTime
                    System.out.println(slot.toLocalTime());
                    found = true;
                }
            }
    
            // If no slots found for the provided date
            if (!found) {
                System.out.println("No time slots found for " + date.toLocalDate());
            }
            
        }

}