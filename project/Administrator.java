package project; 
 
 
import java.util.*; 
 
public class Administrator extends AllUsers { 
    private StaffManager staffManager;
 
    public Administrator(String hospitalId, String password) { 
        super(hospitalId, password, "Administrator");
        this.staffManager = new StaffManager(hospitalId, password, "Administrator");
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
        case 4 -> manageInventory(); 
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
 
    public void manageInventory() {
        System.out.print("Would you like to: \n"  
          + "1: View Inventory \n"  
          + "2: Add Stock \n"  
          + "3: Remove Stock \n" 
          + "4: Update Medication Alert Level \n"  
          + "5: Show Medication Alert Level \n");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt(); 
        switch(option) 
        { 
            case 1 -> InventoryManager.viewInventory(); 
            case 2 -> InventoryManager.addingStock(); 
            case 3 -> InventoryManager.removingStock();
            case 4 -> InventoryManager.alertUpdate(); 
            case 5 -> InventoryManager.showAlert();
        }
    } 
 
    public void approveReplenishmentRequest() { 
        ReplenishmentManager.approveReplenishmentRequest(); 
    } 
}