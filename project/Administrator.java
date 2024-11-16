package project; 
 
 
import java.util.*; 
 
public class Administrator extends AllUsers { 
    private StaffManager staffManager;
    private InventoryManager inventoryManager;
    private ReplenishmentManager replenishmentManager;
	private List<Appointment> appointments = Appointment.getAllAppointments();
    public static List<Administrator> admins = new ArrayList<>();
 
    public Administrator(String hospitalId, String password) { 
        super(hospitalId, password, "Administrator");
        this.staffManager = new StaffManager(hospitalId, password, "Administrator");
        this.inventoryManager = new InventoryManager();
        this.replenishmentManager = new ReplenishmentManager();
        admins.add(this);
    }  
     
    public void displayMenu() 
    { int option; do{
      
     System.out.println("Menu: \n"  
          + "1: Add Staff \n"  
          + "2: Remove Staff \n"  
          + "3: View Doctor's Appointments details \n"  
          + "4: Manage Inventory \n"  
          + "5: Approve Replenishment Requests \n"
          + "6: Filter Staff \n"
          + "7: Logout \n");  
     
    Scanner sc = new Scanner(System.in); 
    option = sc.nextInt(); 
    switch(option) 
    { 
        case 1 -> addStaff(); 
        case 2 -> removeStaff(); 
        case 3 -> viewDoctorAppointments();
        case 4 -> manageInventory();
     	case 5 -> approveReplenishmentRequest(); 
        case 6 -> displayStaff();
        case 7 -> logout(); 
    } 
    }while (option != 7);
    } 
 
    private Object logout() 
    {
	
		return null;
	}

	public void addStaff() { 
        staffManager.addStaff(); 
    } 
 
     
    public void removeStaff() { 
        staffManager.removeStaff(); 
         
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
            case 1 -> inventoryManager.viewInventory(); 
            case 2 -> inventoryManager.addingStock(); 
            case 3 -> inventoryManager.removingStock();
            case 4 -> inventoryManager.alertUpdate(); 
            case 5 -> inventoryManager.showAlert();
        }
    } 
    
    //View Appointment for specific Doctors
    public void viewDoctorAppointments() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the doctor's name to view appointments: ");
        String doctorName = sc.nextLine();  // Get the doctor's name

        // Check if there are appointments for this doctor
        boolean found = false;
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorName().equalsIgnoreCase(doctorName)) {
                System.out.println(appointment.getAppointmentId());  // Display appointmentId detail
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No appointments found for Dr. " + doctorName);
            return;
        }
        
        System.out.println("Please enter the Appointment ID to view details:");
        String chosenAppointmentId = sc.nextLine();

        Appointment selectedAppointment = null;
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(chosenAppointmentId)) {
                selectedAppointment = appointment;
                break;  // Exit loop once the appointment is found
            }
        }

        if (selectedAppointment != null) {
            System.out.println("Details of the selected appointment:");
            System.out.println(selectedAppointment); // Print all details using toString()
        } else {
            System.out.println("Appointment ID not found.");
        }
        
    }

    public void displayStaff()
    {
        Scanner obj = new Scanner(System.in);
        System.out.print("Enter role to filter by. (Enter 'no' if otherwise): ");
        String respond = obj.nextLine();
        String roles;
        if (respond.equals("no"))
        {
            roles = null;
        }
        else
        {
            roles = respond;
        }
        System.out.print("Enter gender to filter by. (Enter 'no' if otherwise): ");
        respond = obj.nextLine();
        String gender;
        if (respond.equals("no"))
        {
            gender = null;
        }
        else
        {
            gender = respond;
        }
        System.out.print("Enter minimum age to filter by: ");
        Integer ageMin = obj.nextInt();
        System.out.print("Enter maximum age to filter by: ");
        Integer ageMax = obj.nextInt();

        StaffManager.displayStaff(roles, gender, ageMin, ageMax);
    }
    
 
    public void approveReplenishmentRequest() { 
       replenishmentManager.approveReplenishmentRequest(); 
 /*// can add/remove stocks in 2 ways - 
  * inventorymanager(can add any amt they want) OR 
  * medicationstock (admin look at request made by pharmacist via replenishmentmanager and add the fixed amt of stock requested by pharmacist)
   */
    } 
}
