package sc2002_proj;

import java.util.Scanner;

public class Pharmacist extends all_users {
    
    public Pharmacist(String hospitalId, String password) {
        super(hospitalId, password, "Pharmacist");
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
    }

    private void updatePrescriptionStatus() {
        System.out.println("Updating Prescription Status...");
        // Implement the logic to update prescription status
    }

    private void viewMedicationInventory() {
        System.out.println("Viewing Medication Inventory...");
        // Implement the logic to view medication inventory
    }

    private void submitReplenishmentRequest() {
        System.out.println("Submitting Replenishment Request...");
        // Implement the logic to submit replenishment request
    }
}
