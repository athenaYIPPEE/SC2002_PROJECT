// hashmap of appointment slots
// dr needs access --

package project;

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

}