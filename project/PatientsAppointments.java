package project;

import java.time.LocalDate;
import java.util.ArrayList;

public class PatientsAppointments extends Patients {
    
    // Constructor
    public PatientsAppointments(String phoneNumber, String emailAddress) {
        super(phoneNumber, emailAddress);
    }

    public void scheduleAppointment(Appointment appointment) {
        this.appointments.add(appointment);
        System.out.println("Appointment scheduled for " + this.getName());
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
