package project;

import java.util.Map;
import java.util.Scanner;

public class ReplenishmentManager {
    private MedicationStock inventory;
    private Map<MedicationName, Integer> inventoryMap;
    
        public ReplenishmentManager() {
            this.inventory = InventoryManager.medicationStock;
            this.inventoryMap = inventory.stock;
        }
    
        public void approveReplenishmentRequest(){
            for (int i = 0; i < InventoryMonitor.replenishmentRequest.size(); i++){
                System.out.println(InventoryMonitor.replenishmentRequest.get(i));
                ReplenishmentRequest request = InventoryMonitor.replenishmentRequest.get(i);
                Scanner scanner = new Scanner(System.in);
                System.out.println("1. Approve   2. Reject");
                int choice = scanner.nextInt();
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
                    Integer choiceInInt = inventoryMap.get(medicationEnum) + replenishmentAmount;
                    //inventoryMap.put(medicationEnum, choiceInInt);
                    InventoryMonitor.replenishmentRequest.remove(i);
                    i--;
                    break;
                case 2:
                    System.out.println("Replenishment Rejected.");
                    InventoryMonitor.replenishmentRequest.remove(i);
                    i--;
                    break;
                default:
                    System.out.println("Invalid Choice."); 
                    break;
                } 
        }
    }

}
