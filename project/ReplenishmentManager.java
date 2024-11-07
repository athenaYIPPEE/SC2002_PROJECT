package project;

import java.util.Scanner;

public class ReplenishmentManager {
    private MedicationStock inventory;

    public ReplenishmentManager(MedicationStock inventory) {
        this.inventory = inventory;
    }

    public void approveReplenishmentRequest(){
        for (int i = 0; i < InventoryManager.replenishmentRequest.size(); i++){
            System.out.println(InventoryManager.replenishmentRequest.get(i));
            ReplenishmentRequest request = InventoryManager.replenishmentRequest.get(i);
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            System.out.println("1. Approve   2. Reject");
            switch (choice){
                case 1: 
                    System.out.println("Replenishment Approved.");
                    String medicationName = request.getName();
                    int replenishmentAmount = request.getQuantity();
                    MedicationName medicationEnum = null;
                    for (MedicationName medication : MedicationName.values()) {
                        if (medication.getName().equals(medicationName)) {
                            medicationEnum = medication;
                            break;
                        }
                    }
                    inventory.addStock(medicationEnum, replenishmentAmount);
                    InventoryManager.replenishmentRequest.remove(i);
                    break;
                case 2:
                    System.out.println("Replenishment Rejected.");
                    InventoryManager.replenishmentRequest.remove(i);
                    break;
                default:
                    System.out.println("Invalid Choice."); 
                    break;
                } 
        }
    }

}
