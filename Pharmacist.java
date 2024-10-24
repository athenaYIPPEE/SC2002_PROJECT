package sc2002_proj;

import java.util.ArrayList;
import java.util.Scanner;

public class Pharmacist extends all_users {
    private ArrayList<String> medicationInventory;
    private ArrayList<String> replenishmentRequests;

    public Pharmacist(String hospitalId, String password) {
        super(hospitalId, password, "Pharmacist");
        this.medicationInventory = new ArrayList<>();
        this.replenishmentRequests = new ArrayList<>();
    }

    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nPharmacist Menu:");
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Medication Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
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
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    private void viewAppointmentOutcomeRecord() {
        System.out.println("Viewing appointment outcome record...");
        // Code to view appointment outcome record
    }

    private void updatePrescriptionStatus() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the appointment ID to update prescription: ");
        String appointmentId = sc.next();
        System.out.println("Updating prescription status for appointment: " + appointmentId);
        // Code to update the prescription status
    }

    private void viewMedicationInventory() {
        System.out.println("Viewing medication inventory...");
        if (medicationInventory.isEmpty()) {
            System.out.println("No medication available.");
        } else {
            for (String med : medicationInventory) {
                System.out.println(med);
            }
        }
    }

    private void submitReplenishmentRequest() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the medication name for replenishment: ");
        String medicationName = sc.next();
        replenishmentRequests.add(medicationName);
        System.out.println("Replenishment request submitted for: " + medicationName);
    }
}

        
                  
