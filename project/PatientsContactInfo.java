package project;

public class PatientsContactInfo {

    public PatientsContactInfo(String hospitalId, String password, String name, String dob, Character gender, String phoneNumber, String emailAddress, String bloodType) {
        super(hospitalId, password, name, dob, gender, phoneNumber, emailAddress, bloodType);
    }

    // Static method to update contact information for a specific patient by name
    public static void updateContactInfo(String name, String newPhoneNumber, String newEmailAddress) {
        Patients patient = Patients.getPatientByName(name);
        if (patient != null) {
            patient.phoneNumber = newPhoneNumber;
            patient.emailAddress = newEmailAddress;
            System.out.println("Contact information updated for " + patient.name);
        } else {
            System.out.println("Patient with name " + name + " not found.");
        }
    }
}
