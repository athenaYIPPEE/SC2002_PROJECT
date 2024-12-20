package project;

import java.util.HashMap;
import java.util.Map;

public class MedicationStock {

	static Map<MedicationName, Integer> stock;
    private static Map<MedicationName, Integer> alert;
                
    public MedicationStock() {
        this.stock = new HashMap<>();
        // Initialize stock with each medication and a default quantity of 0
        for (MedicationName medication : MedicationName.values()) {
            stock.put(medication, 0);
        }

        this.alert = new HashMap<>();

        for (MedicationName medication : MedicationName.values()) {
            alert.put(medication, 50);
        }
    }
            
                
    public void addStock(MedicationName medication, int amount) {
        if (stock.containsKey(medication)) {
            stock.put(medication, stock.get(medication) + amount);
            System.out.println("Added " + amount + " units to " + medication.getName() + ". Current stock: " + stock.get(medication));
        } else {
            System.out.println("Medication " + medication.getName() + " not found in stock.");
        }
    }
            
    public boolean removeStock(MedicationName medication, int amount) {
        if (stock.containsKey(medication)) {
            int currentStock = stock.get(medication);

            if (amount <= currentStock) {
                stock.put(medication, currentStock - amount);
                System.out.println("Removed " + amount + " units from " + medication.getName() + ". Current stock: " + (currentStock - amount));
		return true;    
            } else {
                System.out.println("Insufficient stock. Current stock: " + currentStock);
		return false;
            }

        } else {
            	System.out.println("Medication " + medication.getName() + " not found in stock.");
		return false;
        }
    }
            
    public int getStock(MedicationName medication) {
        return stock.getOrDefault(medication, 0); // Returns 0 if not found
    }

    public void setAlert(MedicationName medication, int level){
        if (alert.containsKey(medication)) {
            alert.put(medication, level);
        } else {
            System.out.println("Invalid Choice.");
        }
    }

    public void showAlert(MedicationName medication){
        if (alert.containsKey(medication)) {
            System.out.println("Alert level is: " + alert.get(medication));
        }
    }

    public Integer getAlert(MedicationName medication) {
        return alert.get(medication);
    }

}
