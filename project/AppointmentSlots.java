// hashmap of appointment slots
// dr needs access --

package project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class AppointmentSlots {
    private static List<LocalDateTime> slots;
        private String doctorName;
    
        public AppointmentSlots(String doctorName){
            this.doctorName = doctorName;
            this.slots = new ArrayList<>();
        }
    
        public static void viewAppointmentSlots(String doctorName){
            for (int i = 0; i < Doctor.doctors.size(); i++){
                if (doctorName.equals(Doctor.doctors.get(i).getName())){
                    System.out.println("Available Appointment Slots: ");
                    for (int j = 0; j < Doctor.appointmentSlots.size(); j++){
                        System.out.println(Doctor.appointmentSlots.get(j));
                    }
                }
            }
    }

        public static void printTimeSlotsForDate(LocalDate date) {
        boolean found = false;
        
        for (LocalDateTime slot : slots) {
            // Compare the date part of LocalDateTime with the input date
            if (slot.toLocalDate().isEqual(date)) {
                // Print the time (hours and minutes) part of LocalDateTime
                System.out.println(slot.toLocalTime());
                found = true;
            }
        }

        // If no slots found for the provided date
        if (!found) {
            System.out.println("No time slots found for " + date);
        }
    }

}