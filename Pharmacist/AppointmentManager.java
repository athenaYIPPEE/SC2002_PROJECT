package Inheritance;


import java.util.List;

public class AppointmentManager {
    // View appointment outcome record for fulfilling prescriptions
    public void viewAppointmentOutcome(Appointment appointment) {
        System.out.println("Viewing appointment outcome for prescription processing:");
        System.out.println(appointment);
        List<Medication> prescribedMedications = appointment.getPrescribedMedications();
        for (Medication medication : prescribedMedications) {
            System.out.println("Medication: " + medication.getName() + ", Status: " + medication.getStatus());
        }
    }

    // Update prescription status to 'dispensed'
    public void updatePrescriptionStatus(Appointment appointment, String medicationName) {
        List<Medication> prescribedMedications = appointment.getPrescribedMedications();
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

