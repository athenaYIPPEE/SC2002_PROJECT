package project;

import java.util.List;

public class Administrator extends AllUsers {
    private StaffManager staffManager;
    private InventoryMonitor inventoryManager;

    public Administrator(String hospitalId, String password, MedicationStock inventory) {
        super(hospitalId, password, "Administrator");
        this.staffManager = new StaffManager();
        this.inventoryManager = new InventoryManager(inventory);
    }

    public void displayMenu(){
        /*view n manage staff
         * view appt details
         * view n manage med inventory
         * approve replenishment req
         */
    }

    public void addStaff(AllUsers staff) {
        staffManager.addStaff(staff);
    }

    public void removeStaff(String hospitalId) {
        staffManager.removeStaff(hospitalId);
    }

    public void viewAppointments(List<Appointment> appointments) {
        System.out.println("Appointments List:");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    public void manageInventory(String medicationName) {
        inventoryManager.manageInventory(medicationName);
    }


