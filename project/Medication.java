package project;

public class Medication {
    private MedicationName name;  // Medication name from enum
    private String status;        // Status, defaulting to "pending"

    // Constructor to initialize Medication with a name and an optional status
    public Medication(MedicationName name) {
        this.name = name;
        this.status = "Pending";  // Default status is "pending"
    }

    // Getter for the medication name
    public String getName() {
        return name.getName();
    }

    // Getter for the medication status
    public String getStatus() {
        return status;
    }

    // Setter for the medication status
    public void setStatus(String status) {
        this.status = status;
    }


}