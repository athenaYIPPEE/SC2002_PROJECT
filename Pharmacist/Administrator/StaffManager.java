package Inheritance;



import java.util.ArrayList;
import java.util.List;

public class StaffManager {
    private List<Doctor> doctors;
    private List<Pharmacist> pharmacists;

    public StaffManager() {
        this.doctors = new ArrayList<>();
        this.pharmacists = new ArrayList<>();
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
}

