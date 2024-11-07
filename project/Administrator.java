package project; 
 
 
import java.util.*; 
 
public class Administrator extends AllUsers { 
    private StaffManager staffManager; 
    private InventoryManager inventoryManager; 
 
    public Administrator(String hospitalId, String password, List<Medication> inventory) { 
        super(hospitalId, password, "Administrator"); 
        this.staffManager = new StaffManager(); 
        this.inventoryManager = new InventoryManager(inventory); 
    } 
     
    public void displayMenu() 
    { 
      
     System.out.println("Menu: \n"  
          + "1: Add Staff \n"  
          + "2: Remove Staff \n"  
          + "3: View Appointments details \n"  
          + "4: View and Manage Medication Inventory  \n"  
          + "5: Approve Replenishment Requests \n"  
          + "6: Logout \n");  
     
    Scanner sc = new Scanner(System.in); 
    int option = sc.nextInt(); 
    switch(option) 
    { 
        case 1 -> addStaff(); 
        case 2 -> removeStaff(); 
        case 3 -> viewAppointments(List<Appointment> appointment); 
        case 4 -> Pharmacist.manageInventory(String medicationName, int newStock); 
        case 5 -> approveReplenishmentRequest(); 
        case 6 -> logout(); 
    } 
    } 
 
    public void addStaff() { 
        staffManager.addStaff(); 
    } 
 
     
    public void removeStaff() { 
     staffManager.removeStaff(); 
         
         
    } 
 
    public void viewAppointments(List<Appointment> appointments) { 
        System.out.println("Appointments List:"); 
        for (Appointment appointment : appointments) { 
            System.out.println(appointment); 
        } 
    } 
 
    public void manageInventory(String medicationName, int newStock) { 
        inventoryManager.manageInventory(medicationName, newStock); 
    } 
 
    public void approveReplenishmentRequest() { 
        ReplenishmentManager.approveReplenishmentRequest(); 
    } 
}