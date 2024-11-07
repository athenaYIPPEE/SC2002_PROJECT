package project;

public class ReplenishmentRequest {
    private int quantity;
    private String medicineName;

    // Constructor
    public ReplenishmentRequest(String medicineName, int quantity) {
        this.medicineName = medicineName;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName(){
        return medicineName;
    }

    @Override
    public String toString() {
        return "ReplenishmentRequest{medicationName='" + medicineName + "', amount=" + quantity + "}";
    }

}
