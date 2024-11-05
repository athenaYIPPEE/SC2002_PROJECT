package project;

public enum Medication {
	ASPIRIN("Aspirin"),
    IBUPROFEN("Ibuprofen"),
    PARACETAMOL("Paracetamol");
	
	private String name;

    Medication(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
