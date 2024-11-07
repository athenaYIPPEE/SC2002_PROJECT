package project;

import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManager {
    private MedicationStock inventory;
    private Medication medication;
    static ArrayList<ReplenishmentRequest> replenishmentRequest = new ArrayList<>();
    static final int lowStock = 10;

    public InventoryManager(MedicationStock inventory) {
        this.inventory = inventory;
    }

    // View medication inventory
    public void viewInventory() {
        System.out.println("Medication Inventory:");
        for (MedicationName medication : MedicationName.values()) {
            System.out.println(medication.getName() + " " + "Stock: " + inventory.getStock(medication));
        }
    }

    // Submit replenishment request
    public void submitReplenishmentRequest(String medicationName) {
        for (MedicationName medication : MedicationName.values()) {
            if (medication.getName().equals(medicationName) && inventory.getStock(medication) < lowStock) {
                System.out.println("How much stock?");
                Scanner scanner = new Scanner(System.in);
                int amt = scanner.nextInt();
                ReplenishmentRequest request = new ReplenishmentRequest(medicationName, amt);
                replenishmentRequest.add(request);    
                System.out.println("Replenishment request submitted for medication: " + medicationName);
            }
        }
        System.out.println("Medication not found or stock is sufficient.");
    }

    // Manage medication inventory
    public void manageInventory(String medicationName) {
        for (MedicationName medication : MedicationName.values()) {
            if (medication.getName().equals(medicationName)) {
                System.out.println("1. Add   2. Remove");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                if(choice == 1){
                    System.out.println("How much stock?");
                    int amt = scanner.nextInt();
                    inventory.addStock(medication, amt);
                } else if (choice == 2) {
                    System.out.println("How much stock?");
                    int amt = scanner.nextInt();
                    inventory.removeStock(medication, amt);
                } else {
                    System.out.println("Invalid selection.");
                }
                System.out.println("Updated stock for " + medicationName + ": " + inventory.getStock(medication));
            }
        }
        System.out.println("Medication not found.");
    }
}
