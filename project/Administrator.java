package project;

import java.util.List;

public class Administrator extends AllUsers {
    private StaffManager staffManager;
    private InventoryManager inventoryManager;

    public Administrator(String hospitalId, String password, List<Medication> inventory) {
        super(hospitalId, password, "Administrator");
        this.staffManager = new StaffManager();
        this.inventoryManager = new InventoryManager(inventory);
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

    public void manageInventory(String medicationName, int newStock) {
        inventoryManager.manageInventory(medicationName, newStock);
    }

    public void approveReplenishmentRequest(String medicationName) {
        inventoryManager.approveReplenishmentRequest(medicationName);
    }
}
