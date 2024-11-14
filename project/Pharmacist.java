package project;

import java.util.List;
import java.util.Scanner;

public class Pharmacist extends AllUsers {
    private AppointmentManager appointmentManager;
    private InventoryManager inventoryManager;

    public Pharmacist(String hospitalId, String password, List<Medication> inventory) {
        super(hospitalId, password, "Pharmacist");
        this.appointmentManager = new AppointmentManager();
    }
    
    public void DisplayMenu()
    { int option; do{
    	Scanner sc = new Scanner(System.in);
        System.out.println("Menu: \n" 
    		  
    		    + "1: View Appointment Outcome Record \n" 
    		    + "2: Update Prescription Status \n" 
    		    + "3: View Medication Inventory \n" 
    		    + "4: Submit Replenishment Request \n"
    		    + "5: Logout \n"); 
        
    	option = sc.nextInt();
    	
    	switch(option)
    	{
    	case 1 -> viewAppointmentOutcome();
    	case 2 -> updatePrescriptionStatus();
    	case 3 -> viewInventory();
    	case 4 -> submitReplenishmentRequest();
    	case 5 -> logout();
    	}
    			
    }while (option != 5);
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
        InventoryMonitor.submitReplenishmentRequest();
    }

    public void logout()
    {

    }
}
