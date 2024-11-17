package project;

import java.util.ArrayList;
import java.util.Scanner;

// pharmacist

public class InventoryMonitor {
        private MedicationStock inventory;
        private Medication medication;
        static ArrayList<ReplenishmentRequest> replenishmentRequest = new ArrayList<>();
    
        public InventoryMonitor() {
            this.inventory = InventoryManager.medicationStock;
        }
    
        // View medication inventory
        public void viewInventory() {
            System.out.println("Medication Inventory:");
            for (MedicationName medication : MedicationName.values()) {
                System.out.println(medication.getName() + " " + "Stock: " + inventory.getStock(medication));
            }
        }
    
        // Submit replenishment request
        public void submitReplenishmentRequest() {
            System.out.print("Enter Medication Name: ");
            Scanner sc = new Scanner(System.in);
            String medicationName = sc.nextLine();
            boolean found = false;
            for (MedicationName medication : MedicationName.values()) {
                if (medication.getName().equals(medicationName) && inventory.getStock(medication) < inventory.getAlert(medication)) {
                found = true;
            	System.out.println("How much stock to add to " + medicationName + "?");
                int amt = sc.nextInt();
                ReplenishmentRequest request = new ReplenishmentRequest(medicationName, amt);
                replenishmentRequest.add(request);    
                System.out.println("Replenishment request submitted for medication: " + medicationName);
                }   
            }
    	if (found == false) System.out.println("Medication not found or stock is sufficient.");
    }
    // Manage medication inventory
}
