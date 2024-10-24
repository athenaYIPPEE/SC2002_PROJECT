package sc2002_proj;

import java.util.ArrayList;
import java.util.Scanner;

public class Administrator extends all_users {
    private ArrayList<String> staffList;
    private ArrayList<String> medicationInventory;
    private ArrayList<String> appointmentList;

    public Administrator(String hospitalId, String password) {
        super(hospitalId, password, "Administrator");
        this.staffList = new ArrayList<>();
        this.medicationInventory = new ArrayList<>();
        this.appointmentList = new ArrayList<>();
    }

    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nAdministrator Menu:");
            System.out.println("1. View and Manage Hospital Staff");
            System.out.println("2. View Appointments Details");
            System.out.println("3. View and Manage Medication Inventory");
            System.out.println("4. Approve Replenishment Requests");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    manageHospitalStaff();
                    break;
                case 2:
                    viewAppointmentsDetails();
                    break;
                case 3:
                    manageMedicationInventory();
                    break;
                case 4:
                    approveReplenishmentRequests();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    private void manageHospitalStaff() {
        System.out.println("Managing hospital staff...");
        // Code to view, add, update, or remove staff members
    }

    private void viewAppointmentsDetails() {
        System.out.println("Viewing appointment details...");
        if (appointmentList.isEmpty()) {
            System.out.println("No appointments available.");
        } else {
            for (String appointment : appointmentList) {
                System.out.println(appointment);
            }
        }
    }

    private void manageMedicationInventory() {
        System.out.println("Managing medication inventory...");
        if (medicationInventory.isEmpty()) {
            System.out.println("No medications available.");
        } else {
            for (String medication : medicationInventory) {
                System.out.println(medication);
            }
        }
    }

    private void approveReplenishmentRequests() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the replenishment request to approve: ");
        String request = sc.next();
        System.out.println("Replenishment request " + request + " approved.");
        // Code to approve the request and update the medication inventory
    }
}
