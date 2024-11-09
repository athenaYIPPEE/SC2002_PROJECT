package project;

import java.util.List;
import java.util.Scanner;

public class AppointmentManager {
    private List<Appointment> appointments = Appointment.getAllAppointments();

    // View appointment outcome record for fulfilling prescriptions
    public void viewAppointmentOutcome() {
        System.out.print("Enter appointment id: ");
        Scanner sc = new Scanner(System.in);
        String apptid = sc.nextLine();

        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equalsIgnoreCase(apptid)) {
                System.out.println("Viewing appointment outcome for prescription processing:");
                System.out.println(appointment);
                List<Medication> prescribedMedications = appointment.getPrescribedMedicationList();
                for (Medication medication : prescribedMedications) {
                    System.out.println("Medication: " + medication.getName() + ", Status: " + medication.getStatus());
                }
                break;
            }
        }
    }

    // Update prescription status to 'dispensed'
    public void updatePrescriptionStatus() {
        System.out.print("Enter appointment id: ");
        Scanner sc = new Scanner(System.in);
        String apptid = sc.nextLine();

        boolean found = false;
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equalsIgnoreCase(apptid)) {
                System.out.println(appointment.getAppointmentId());  // Display appointmentId detail
                found = true;
                List<Medication> prescribedMedications = appointment.getPrescribedMedicationList();
                System.out.print("Enter medicine name: ");
                String medicationName = sc.nextLine();
                for (Medication medication : prescribedMedications) {
                    if (medication.getName().equals(medicationName)) {
                    medication.setStatus("Dispensed");
                    System.out.println("Prescription status updated to 'Dispensed' for medication: " + medicationName);
                    return;
                     }
                }
            System.out.println("Medication not found in prescription.");
            }
        }
        
        if (!found) {
            System.out.println("No appointments found. ");
            return;
        }
    }
}

