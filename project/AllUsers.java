package project;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AllUsers {
    private String hospitalId;
    private String password;
    private String role;
    
    public AllUsers(String hospitalId, String password, String role) {
        this.hospitalId = hospitalId;
        this.password = password;
        this.role = role;
    }
    
    protected static HashMap<String, String> user = new HashMap<>(); //smth like a dictionary, <id, password>
    protected static Map<String, StaffInfo> userInfoMap = new HashMap<>(); // <hospitalId, StaffInfo>

    public String getHospitalId() {
        return hospitalId;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
	
	public static String login()
	{
		Scanner obj = new Scanner(System.in);
		
		System.out.print("Enter your Hospital ID: ");
		String hospital_id = obj.next();
		System.out.print("Enter your password: ");
		String password = obj.next(); //default password is 'password'. this is done by staff management under administrator(so no need initialise as pw=password)

		if (!user.containsKey(hospital_id))
		{
			System.out.println("User does not exist.");
            System.exit(0);
		}
		else if (user.get(hospital_id).equals(password)) 
		{
            System.out.println("Login successful!");
            return hospital_id;
        } 
		else 
		{
            System.out.println("Invalid password. Please try again.");
            System.exit(0);
        }
        return null;
	}
	
	public static void changeUserPassword(String hospital_id)
	{
		Scanner obj = new Scanner(System.in);
		System.out.print("Enter your current password: ");
		String cur_pw = obj.next();
		if (!user.get(hospital_id).equals(cur_pw)) {
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
		
		user.put(hospital_id, new_pw);
		System.out.println("Password updated!");
	}
	
	
    //no soLid in all_user bcos it's a superclass

}

