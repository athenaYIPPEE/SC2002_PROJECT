package project;

import java.time.LocalDate;
import java.util.ArrayList;

public class PatientsContactInfo extends Patients {
    
    public PatientsContactInfo( String phoneNumber, String emailAddress) {
        super(phoneNumber, emailAddress);
    }

    public void updateContactInfo(String newPhoneNumber, String newEmailAddress) {
        this.phoneNumber = newPhoneNumber;
        this.emailAddress = newEmailAddress;
        System.out.println("Contact information updated for " + this.name);
    }
}