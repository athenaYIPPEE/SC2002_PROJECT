package project;


import java.util.List;

public class InventoryManager {
    private List<Medication> inventory;

    public InventoryManager(List<Medication> inventory) {
        this.inventory = inventory;
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

    // Manage medication inventory
    public void manageInventory(String medicationName, int newStock) {
        for (Medication medication : inventory) {
            if (medication.getName().equals(medicationName)) {
                medication.setStock(newStock);
                System.out.println("Updated stock for " + medicationName + ": " + newStock);
                return;
            }
        }
        System.out.println("Medication not found.");
    }
}
