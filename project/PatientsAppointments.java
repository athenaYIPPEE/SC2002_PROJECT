package project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class PatientsAppointments {

    // Constructor

    public PatientsAppointments(String phoneNumber, String emailAddress) {
        this.PatientAppointmentList = new ArrayList();
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
        System.out.println("Enter a date (YYYY-MM-DD): ");
        String inputDate = scanner.nextLine();
        // Parse the input date into a LocalDate
        LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE);
        // Call method to print time slots for that date
        AppointmentSlots.printTimeSlotsForDate(date);
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
