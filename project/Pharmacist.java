package project;

import java.util.List;
import java.util.Scanner;

public class Pharmacist extends AllUsers {
    private AppointmentManager appointmentManager;
    private InventoryManager inventoryManager;

    public Pharmacist(String hospitalId, String password, List<Medication> inventory) {
        super(hospitalId, password, "Pharmacist");
        this.appointmentManager = new AppointmentManager();
        this.inventoryManager = new InventoryManager(inventory);
    }
    
    public void DisplayMenu()
    {
    	System.out.print("Select an Appointment");
    	Scanner sc = new Scanner(System.in);
    	int choice = sc.nextInt();
    	
        System.out.println("Menu: \n" 
    		  
    		    + "1: View Appointment Outcome Record \n" 
    		    + "2: Update Prescription Status \n" 
    		    + "3: View Medication Inventory \n" 
    		    + "4: Submit Replenishment Request \n"
    		    + "5: Logout \n"); 
        
    	Scanner sc = new Scanner(System.in);
    	int option = sc.nextInt();
    	
    	switch(option)
    	{
    	case 1 -> viewAppointmentOutcome();
    	case 2 -> updatePrescriptionStatus();
    	case 3 -> viewInventory();
    	case 4 -> submitReplenishmentRequest(String medicationName);
    	case 5 -> logout();
    	}
    			
    	
    }

    public void viewAppointmentOutcome() {
        appointmentManager.viewAppointmentOutcome();
    }

    public void updatePrescriptionStatus() {
        appointmentManager.updatePrescriptionStatus();
    }

    public void viewInventory() {
        inventoryManager.viewInventory();
    }

    public void submitReplenishmentRequest() {
        System.out.print("Enter Mediaction Name");
        Scanner sc = new Scanner(System.in);
        String medicationName = sc.nextLine();
        InventoryMonitor.submitReplenishmentRequest(medicationName);
    }

    public void logout()
    {
        
    }
}
