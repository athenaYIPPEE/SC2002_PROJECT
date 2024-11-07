package project;



import java.util.*;
import java.util.List;
import java.util.Scanner;

public class StaffManager extends AllUsers{
//    private List<Doctor> doctors;
//    private List<Pharmacist> pharmacists;

	public StaffManager(String hospitalId, String password, String role) 
	{
		super(hospitalId, password, role);// Setting "StaffManager" as the role
    }


    // Manage hospital staff
    public void addStaff() {
        /*if (staff instanceof Doctor) {
           (doctors.add((Doctor) staff);
            System.out.println("Doctor added: " + staff.getHospitalId());
        } else if (staff instanceof Pharmacist) {
            pharmacists.add((Pharmacist) staff);
            System.out.println("Pharmacist added: " + staff.getHospitalId());
        }*/
    	
        System.out.print("Enter the Hospital ID of the new Staff: ");
        Scanner sc = new Scanner(System.in);
        String hospitalId = sc.nextLine();
        AllUsers.user.put(hospitalId, "password");
    }

    public void removeStaff() {
    	System.out.print("Enter Hospital ID of the Staff to be removed:" );
    	Scanner sc = new Scanner(System.in);
    	String hospitalId = sc.nextLine();
        /*doctors.removeIf(doctor -> doctor.getHospitalId().equals(hospitalId));
        pharmacists.removeIf(pharmacist -> pharmacist.getHospitalId().equals(hospitalId));
        System.out.println("Staff with ID " + hospitalId + " removed.");*/
    	
    	if (AllUsers.user.containsKey(hospitalId)) {
            AllUsers.user.remove(hospitalId); // Directly removes from user HashMap in AllUsers
            System.out.println("Staff with ID " + hospitalId + " removed from the system.");
        } else {
            System.out.println("No staff found with ID " + hospitalId);
        }
    }
}

