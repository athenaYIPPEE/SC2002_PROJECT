package project;

import java.util.Scanner;

public class InventoryManager {
    private int lowStockAlert;
    private static MedicationStock inventory;
        private MedicationName medication;
        private Medication medicationlevel;
    
        /*view and manage inventory 
         * aka adding removing updating stock
         * update low level alert line 
         * for administrator
         */
    
        public InventoryManager(MedicationStock inventory){
            this.inventory = inventory;
        }
        
         public static void viewInventory() {
            System.out.println("Medication Inventory:");
            for (MedicationName medication : MedicationName.values()) {
                System.out.println(medication.getName() + " " + "Stock: " + inventory.getStock(medication));
            }
        }
    
        public static void addingStock(){
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
            inventory.addStock(medicationEnum, amt);
    }

    public static void removingStock(){
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
        inventory.removeStock(medicationEnum, amt);
    }

    public static void alertUpdate(){
        System.out.println("Which Medication?");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        MedicationName medicationEnum = null;
        for (MedicationName medication : MedicationName.values()) {
            if (medication.getName().equals(choice)) {
                medicationEnum = medication;}
            }
        System.out.println("New Alert Level?");
        int level = scanner.nextInt();
        inventory.setAlert(medicationEnum, level);
    }

    public static void showAlert(){
        System.out.println("Which Medication?");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        MedicationName medicationEnum = null;
        for (MedicationName medication : MedicationName.values()) {
            if (medication.getName().equals(choice)) {
                medicationEnum = medication;}
            }
        inventory.showAlert(medicationEnum);
    }


}
