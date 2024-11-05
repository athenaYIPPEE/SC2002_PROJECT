package Inheritance;

package users;

import java.util.List;

public class ReplenishmentManager {
    private List<Medication> inventory;

    public ReplenishmentManager(List<Medication> inventory) {
        this.inventory = inventory;
    }

    // Approve replenishment requests from pharmacists
    public void approveReplenishmentRequest(String medicationName) {
        for (Medication medication : inventory) {
            if (medication.getName().equals(medicationName)) {
                int restockedAmount = medication.getLowStockAlertLevel() + 50; // Example replenishment amount
                medication.setStock(restockedAmount);
                System.out.println("Replenishment approved for " + medicationName + ". New stock: " + restockedAmount);
                return;
            }
        }
        System.out.println("Replenishment request for " + medicationName + " not found.");
    }
}

