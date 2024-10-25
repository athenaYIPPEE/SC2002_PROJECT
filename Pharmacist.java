package users;

import java.util.List;
import java.util.ArrayList;

public class Pharmacist extends All_users {
    private List<Appointment> appointments; // To handle prescriptions
    private List<Medication> inventory; // Medication inventory for tracking stock levels
    
    public Pharmacist(String hospitalId, String password) {
        super(hospitalId, password, "Pharmacist");
        this.appointments = new ArrayList<>();
        this.inventory = new ArrayList<>();
    }

    // View appointment outcome record for fulfilling prescriptions
    public void viewAppointmentOutcome(Appointment appointment) {
        System.out.println("Viewing appointment outcome for prescription processing:");
        System.out.println(appointment);
        // Logic to view prescribed medications from the appointment's outcome
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

    // View medication inventory
    public void viewInventory() {
        System.out.println("Medication Inventory:");
        for (Medication medication : inventory) {
            System.out.println("Medication: " + medication.getName() + ", Stock: " + medication.getStock());
        }
    }

    // Submit replenishment request
    public void submitReplenishmentRequest(String medicationName) {
        for (Medication medication : inventory) {
            if (medication.getName().equals(medicationName) && medication.getStock() < medication.getLowStockAlertLevel()) {
                System.out.println("Replenishment request submitted for medication: " + medicationName);
                return;
            }
        }
        System.out.println("Medication not found or stock is sufficient.");
    }
}
