package labproject;

public class Medication {
	
	private String name;
	private String status;
	private int stock;
	
	public Medication(String name, int stock) {
		this.name = name;
		this.status = "pending";
		this.stock = stock;
	}	
	
	//getters
	public String getName() {
        return name;
    }

    public int getStockLevel() {
        return stock;
    }
    
    public String getStatus() {
        return status;
    }
	
	public void setStatus(String status) { //pending to dispensed
		this.status = status;
	}
	
	public void addStock(int amount) {
		this.stock += amount;
        System.out.println("Added " + amount + " units of " + name + ". Current stock: " + this.stock);
	}
	
	public void removeStock(int amount) {
		if (amount <= stock) {
            this.stock -= amount;
            System.out.println("Removed " + amount + " units of " + name + ". Current stock: " + this.stock);
        } else {
            System.out.println("Insufficient stock. Current stock: " + this.stock);
        }
	}
	
}
