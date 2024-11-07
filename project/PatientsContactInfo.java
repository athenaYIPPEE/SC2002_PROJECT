package project;

import java.time.LocalDate;
import java.util.ArrayList;

public class PatientsContactInfo extends Patients {
    
    public PatientsContactInfo( String phoneNumber, String emailAddress) {
        super(phoneNumber, emailAddress, "Alice Brown", "1980-05-14", "Female", "1234567890", "alice.brown@example.com", "A+");
    }

    public void updateContactInfo(String newPhoneNumber, String newEmailAddress) {
        this.phoneNumber = newPhoneNumber;
        this.emailAddress = newEmailAddress;
        System.out.println("Contact information updated for " + this.name);
    }
}