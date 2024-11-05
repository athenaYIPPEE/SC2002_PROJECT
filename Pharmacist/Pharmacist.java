package Inheritance;



import java.util.List;

public class Pharmacist extends All_users {
    private AppointmentManager appointmentManager;
    private InventoryManager inventoryManager;

    public Pharmacist(String hospitalId, String password, List<Medication> inventory) {
        super(hospitalId, password, "Pharmacist");
        this.appointmentManager = new AppointmentManager();
        this.inventoryManager = new InventoryManager(inventory);
    }

    public void viewAppointmentOutcome(Appointment appointment) {
        appointmentManager.viewAppointmentOutcome(appointment);
    }

    public void updatePrescriptionStatus(Appointment appointment, String medicationName) {
        appointmentManager.updatePrescriptionStatus(appointment, medicationName);
    }

    public void viewInventory() {
        inventoryManager.viewInventory();
    }

    public void submitReplenishmentRequest(String medicationName) {
        inventoryManager.submitReplenishmentRequest(medicationName);
    }
}
