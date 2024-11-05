package project;

import java.util.Scanner;
import java.util.HashMap;

public class All_Users {
    private String hospitalId;
    private String password;
    private String role;
    
    public All_Users(String hospitalId, String password, String role) {
        this.hospitalId = hospitalId;
        this.password = password;
        this.role = role;
    }
    
    private static HashMap<String, String> user = new HashMap<>(); //smth like a dictionary, <id, password>

    public String getHospitalId() {
        return hospitalId;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
	
	public void login()
	{
		Scanner obj = new Scanner(System.in);
		
		System.out.print("Enter your Hospital ID: ");
		String hospital_id = obj.next();
		System.out.print("Enter your password: ");
		String password = obj.next(); //default password is 'password'. this is done by staff management under administrator(so no need initialise as pw=password)
		
		if (!user.containsKey(hospital_id))
		{
			System.out.println("User does not exist.");
		}
		else if (user.get(hospital_id).equals(password)) 
		{
            System.out.println("Login successful!");
        } 
		else 
		{
            System.out.println("Invalid password. Please try again.");
        }
		
	}
	
	public void resetPassword() //soliD, abstraction
	{
	    changePassword managePassword = new changePassword(user);
	    managePassword.changeUserPassword(this.hospitalId);
	}
	
	public void roleSpecificAccess() //sort of soliD?, high level module
	{
        switch (this.getRole()) {
            case "Patient":
                ((Patient) this).displayMenu();
                break;
            case "Doctor":
                ((Doctor) this).displayMenu();
                break;
            case "Pharmacist":
                ((Pharmacist) this).displayMenu();
                break;
            case "Administrator":
                ((Administrator) this).displayMenu();
                break;
        }
    }
	
    //no soLid in all_user bcos it's a superclass

}

