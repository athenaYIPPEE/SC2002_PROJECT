package project;

import java.util.Map;
import java.util.Scanner;

public class InventoryManager {
    private int lowStockAlert;
    private static Map<MedicationName, Integer> inventory;
    protected static MedicationStock medicationStock;
            
    /*view and manage inventory 
    * aka adding removing updating stock
    * update low level alert line 
    * for administrator
    */

    public InventoryManager(){
        this.medicationStock = new MedicationStock();
        this.inventory = medicationStock.stock;
    }
    
    public void viewInventory() {
        System.out.println("Medication Inventory:");

        for (Map.Entry<MedicationName, Integer> entry : inventory.entrySet()) {
            MedicationName medication = entry.getKey();
            Integer quantity = entry.getValue();
            System.out.println("Medication: " + medication + ", Quantity: " + quantity);
        }
    }

    public void addingStock(){
        System.out.println("Which Medication?");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        MedicationName medicationEnum = null;

        for (MedicationName medication : MedicationName.values()) {
            if (medication.getName().equals(choice)) {
                medicationEnum = medication;}
            }
        System.out.println("How much?");
        int amt = scanner.nextInt();
        medicationStock.addStock(medicationEnum, amt);                
    }

    public void removingStock(){
        System.out.println("Which Medication?");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        MedicationName medicationEnum = null;

        for (MedicationName medication : MedicationName.values()) {
            if (medication.getName().equals(choice)) {
                medicationEnum = medication;
            }
        }

        System.out.println("How much?");
        int amt = scanner.nextInt();
        medicationStock.removeStock(medicationEnum, amt);
        Integer choiceInInt = inventory.get(medicationEnum) - amt;
        inventory.put(medicationEnum, choiceInInt);
    }

    public void alertUpdate(){
        System.out.println("Which Medication?");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        MedicationName medicationEnum = null;

        for (MedicationName medication : MedicationName.values()) {
            if (medication.getName().equals(choice)) {
                medicationEnum = medication;
            }
        }

        System.out.println("New Alert Level?");
        int level = scanner.nextInt();
        medicationStock.setAlert(medicationEnum, level);
    }

    public void showAlert(){
        System.out.println("Which Medication?");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        MedicationName medicationEnum = null;

        for (MedicationName medication : MedicationName.values()) {
            if (medication.getName().equals(choice)) {
                medicationEnum = medication;
            }
        }
        medicationStock.showAlert(medicationEnum);
    }
}
