package sc2002_proj;

import java.util.Scanner;
import java.util.HashMap;

public class changePassword{
	private HashMap<String, String> users;
	
	public changePassword(HashMap<String, String> users) 
	{
        this.users = users;
    }
	
	public void changeUserPassword(String hospital_id)
	{
		Scanner obj = new Scanner(System.in);
		
		if (!users.containsKey(hospital_id)) //just in case function calls invalid id
		{
			System.out.println("Incorrect username, please try again.");
            return;
		}
		
		System.out.print("Enter your current password: ");
		String cur_pw = obj.next();
		if (!users.get(hospital_id).equals(cur_pw)) {
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
		
		users.put(hospital_id, new_pw);
		System.out.println("Password updated!");
	}

}
