package sc2002_proj;

import java.util.Scanner;
import java.util.HashMap;

public class all_users {
	//testing github
	private String hospitalId;
    private String password;
    private String role;
    
    public all_users(String hospitalId, String password, String role) {
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
	
	public void changePassword(String hospital_id)
	{
		Scanner obj = new Scanner(System.in);
		
		System.out.print("Enter your current password: ");
		String cur_pw = obj.next();
		if (!this.password.equals(cur_pw)) {
            System.out.println("Incorrect password, please try again.");
            return;
        }
		System.out.print("Enter your new password: ");
		String new_pw = obj.next();
		System.out.print("Confirm your new password: ");
		String cfm_new_pw = obj.next();
		
		while (!new_pw.equals(cfm_new_pw))
		{
			System.out.println("Passwords do not match, please try again!");
			System.out.print("Enter your new password: ");
			new_pw = obj.next();
			System.out.print("Confirm your new password: ");
			cfm_new_pw = obj.next();
		}
		
		this.password = new_pw;
		user.put(hospital_id, new_pw);
		System.out.println("Password updated!");
	}
	
	public void roleSpecificAccess() {
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
	

}
