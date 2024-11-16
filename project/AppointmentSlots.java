// hashmap of appointment slots
// dr needs access --

package project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class AppointmentSlots {
    private List<LocalDateTime> slots;
        private String doctorName;
    
        public AppointmentSlots(String doctorName){
            this.doctorName = doctorName;
            this.slots = new ArrayList<>();
        }

        // Method to add a slot
        public void addSlot(LocalDateTime slot) {
            this.slots.add(slot);
        }

        // Get the slots for this doctor
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
            for (Doctor doctor : Doctor.doctors) {
                if (doctorName.equals(doctor.getName())) {
                    found = true;
                    AppointmentSlots appointmentSlots = doctor.getAppointmentSlots(); // Get this doctor's AppointmentSlots
                    System.out.println("Available Appointment Slots for " + doctorName + ": ");
                    
                    for (LocalDateTime slot : appointmentSlots.getSlots()) {
                        System.out.println(slot); // Print each slot
                    }
                }
            }
            if (found == false) System.out.println("Doctor not found.");
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