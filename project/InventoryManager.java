package project;

public class InventoryManager {
    private MedicationStock inventory;
    private Medication medication;

    public InventoryManager(MedicationStock inventory) {
        this.inventory = inventory;
    }

    // View medication inventory
    public void viewInventory() {
        System.out.println("Medication Inventory:");
        for (Medication medication : Medication.values()) {
            System.out.println( "Stock: " + inventory.getStock(medication));
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
