package project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

// 0 = hospitalid
// 1 = password
// 2 = name
// 3= age
// 4 = gender
// 5 = dob 
// 6 = role


public class main {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\athen\\OneDrive\\Documents\\GitHub\\SC2002_PROJECT\\project\\Users.txt"; // specify your file path
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null){
                String[] userData = line.split("\\|");
                AllUsers.user.put(userData[0], userData[1]);
                int age = Integer.parseInt(userData[3]);
                StaffInfo staffInfo = new StaffInfo(userData[0], age, userData[6], userData[2], userData[5], userData[4]);
                AllUsers.userInfoMap.put(userData[0], staffInfo);
            }

            String thisUserId= AllUsers.login();
            while ((line = br.readLine()) != null){
                String[] userData = line.split("\\|");
                if (userData[0].equals(thisUserId)){
                    String role = userData[6];

                    switch(role){
                        case "Doctor":
                            Doctor doctor = new Doctor(thisUserId, userData[1], userData[6], userData[2]);
                            doctor.displayMenu();
                        case "Administrator":
                            Administrator administrator = new Administrator(thisUserId, userData[1]);
                            administrator.displayMenu();
                        case "Pharmacist":
                            Pharmacist pharmacist = new Pharmacist(thisUserId, role, null);
                            pharmacist.DisplayMenu();
                        default:
                            System.out.println("nah bro");
                    }
                }
            }

        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
