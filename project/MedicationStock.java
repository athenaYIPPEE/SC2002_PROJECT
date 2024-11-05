package project;

import java.util.HashMap;
import java.util.Map;

public class MedicationStock {

	private Map<Medication, Integer> stock;
	
	public MedicationStock() {
        this.stock = new HashMap<>();
	    // Initialize stock with each medication and a default quantity of 0
	    for (Medication medication : Medication.values()) {
	        stock.put(medication, 0);
	    }
    }
	
	public void addStock(Medication medication, int amount) {
        if (stock.containsKey(medication)) {
            stock.put(medication, stock.get(medication) + amount);
            System.out.println("Added " + amount + " units to " + medication.getName() + ". Current stock: " + stock.get(medication));
        } else {
            System.out.println("Medication " + medication.getName() + " not found in stock.");
        }
    }
	
	public void removeStock(Medication medication, int amount) {
        if (stock.containsKey(medication)) {
            int currentStock = stock.get(medication);
            if (amount <= currentStock) {
                stock.put(medication, currentStock - amount);
                System.out.println("Removed " + amount + " units from " + medication.getName() + ". Current stock: " + (currentStock - amount));
            } else {
                System.out.println("Insufficient stock. Current stock: " + currentStock);
            }
        } else {
            System.out.println("Medication " + medication.getName() + " not found in stock.");
        }
    }
	
	public int getStock(Medication medication) {
        return stock.getOrDefault(medication, 0); // Returns 0 if not found
    }
	
}
