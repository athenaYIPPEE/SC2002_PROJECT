package project;

public enum MedicationName {
	ASPIRIN("Aspirin"),
    IBUPROFEN("Ibuprofen"),
    PARACETAMOL("Paracetamol");
	
	private String name;

    MedicationName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
