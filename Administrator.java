package users;

import java.util.List;
import java.util.ArrayList;

public class Administrator extends All_users {
    private List<Doctor> doctors;
    private List<Pharmacist> pharmacists;
    private List<Medication> inventory;

    public Administrator(String hospitalId, String password) {
        super(hospitalId, password, "Administrator");
        this.doctors = new ArrayList<>();
        this.pharmacists = new ArrayList<>();
        this.inventory = new ArrayList<>();
    }

    // Manage hospital staff
    public void addStaff(All_users staff) {
        if (staff instanceof Doctor) {
            doctors.add((Doctor) staff);
            System.out.println("Doctor added: " + staff.getHospitalId());
        } else if (staff instanceof Pharmacist) {
            pharmacists.add((Pharmacist) staff);
            System.out.println("Pharmacist added: " + staff.getHospitalId());
        }
    }

    public void removeStaff(String hospitalId) {
        doctors.removeIf(doctor -> doctor.getHospitalId().equals(hospitalId));
        pharmacists.removeIf(pharmacist -> pharmacist.getHospitalId().equals(hospitalId));
        System.out.println("Staff with ID " + hospitalId + " removed.");
    }

    // View appointments (with details)
    public void viewAppointments(List<Appointment> appointments) {
        System.out.println("Appointments List:");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    // Manage medication inventory
    public void manageInventory(String medicationName, int newStock) {
        for (Medication medication : inventory) {
            if (medication.getName().equals(medicationName)) {
                medication.setStock(newStock);
                System.out.println("Updated stock for " + medicationName + ": " + newStock);
                return;
            }
        }
        System.out.println("Medication not found.");
    }

    // Approve replenishment requests from pharmacists
    public void approveReplenishmentRequest(String medicationName) {
        for (Medication medication : inventory) {
            if (medication.getName().equals(medicationName)) {
                // Assuming some logic here to approve request
                int restockedAmount = medication.getLowStockAlertLevel() + 50; // Example replenishment amount
                medication.setStock(restockedAmount);
                System.out.println("Replenishment approved for " + medicationName + ". New stock: " + restockedAmount);
                return;
            }
        }
        System.out.println("Replenishment request for " + medicationName + " not found.");
    }
}
