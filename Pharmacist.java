package sc2002_proj;

import java.util.HashMap;
import java.util.Scanner;

public class Pharmacist extends all_users {
    
    private HashMap<String, Appointment> appointmentOutcomeRecords; // Store appointment outcomes
    private HashMap<String, Medication> medicationInventory; // Store medication inventory

    public Pharmacist(String hospitalId, String password) {
        super(hospitalId, password, "Pharmacist");
        this.appointmentOutcomeRecords = new HashMap<>();
        this.medicationInventory = new HashMap<>();
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nPharmacist Menu:");
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Medication Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. Change Password");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAppointmentOutcomeRecord();
                    break;
                case 2:
                    updatePrescriptionStatus();
                    break;
                case 3:
                    viewMedicationInventory();
                    break;
                case 4:
                    submitReplenishmentRequest();
                    break;
                case 5:
                    changePassword(this.getHospitalId());
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private void viewAppointmentOutcomeRecord() {
        System.out.println("Viewing Appointment Outcome Record...");
        // Implement the logic to view appointment outcome records
        if (appointmentOutcomeRecords.isEmpty()) {
            System.out.println("No appointment outcome records available.");
            return;
        }
        for (String key : appointmentOutcomeRecords.keySet()) {
            Appointment appointment = appointmentOutcomeRecords.get(key);
            System.out.println(appointment); // Assuming Appointment has a proper toString method
        }
    }

    private void updatePrescriptionStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the appointment ID for the prescription: ");
        String appointmentID = scanner.nextLine();

        Appointment appointment = appointmentOutcomeRecords.get(appointmentID);
        if (appointment == null) {
            System.out.println("Appointment not found.");
            return;
        }

        System.out.print("Enter new prescription status (e.g., pending, dispensed): ");
        String status = scanner.nextLine();
        appointment.setPrescriptionStatus(status); // Assuming Appointment has setPrescriptionStatus method
        System.out.println("Prescription status updated to: " + status);
    }

    private void viewMedicationInventory() {
        System.out.println("Viewing Medication Inventory...");
        // Implement the logic to view medication inventory
        if (medicationInventory.isEmpty()) {
            System.out.println("No medications available in inventory.");
            return;
        }
        for (String key : medicationInventory.keySet()) {
            Medication medication = medicationInventory.get(key);
            System.out.println(medication); // Assuming Medication has a proper toString method
        }
    }

    private void submitReplenishmentRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter medication name for replenishment: ");
        String medicationName = scanner.nextLine();

        Medication medication = medicationInventory.get(medicationName);
        if (medication == null) {
            System.out.println("Medication not found in inventory.");
            return;
        }

        System.out.print("Enter quantity to replenish: ");
        int quantity = scanner.nextInt();
        medication.replenish(quantity); // Assuming Medication has a replenish method
        System.out.println("Replenishment request submitted for " + quantity + " units of " + medicationName);
    }
}
