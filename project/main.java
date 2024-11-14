package project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

// 0 = hospitalid
// 1 = password
// 2 = name
// 3= age
// 4 = gender
// 5 = dob 
// 6 = role

/* 
id 0
password 1
role2
name3
dob4
gender5
phone6
email7
blood8 */

public class main {
    public static void main(String[] args) {
        boolean isLoggedIn = true;
        String staffListName = "C:\\Users\\athen\\OneDrive\\Documents\\GitHub\\SC2002_PROJECT\\project\\StaffList.txt"; // specify your file path
        String patientListName = "C:\\Users\\athen\\OneDrive\\Documents\\GitHub\\SC2002_PROJECT\\project\\PatientList.txt"; // specify your file path
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(staffListName)))) {
            String line;

            while ((line = br.readLine()) != null){
                String[] userData = line.split("\\|");
                AllUsers.user.put(userData[0], userData[1]);
                int age = Integer.parseInt(userData[3]);
                StaffInfo staffInfo = new StaffInfo(userData[0], age, userData[6], userData[2], userData[5], userData[4]);
                AllUsers.userInfoMap.put(userData[0], staffInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br3 = new BufferedReader(new InputStreamReader(new FileInputStream(patientListName)))) {
            String line3;

            while ((line3 = br3.readLine()) != null){
                String[] patientData = line3.split("\\|");
                Patients patient = new Patients(patientData[0], patientData[1], patientData[2], patientData[3], patientData[4], patientData[5], patientData[6], patientData[7], patientData[8]);
                Patients.patientList.put(patientData[0], patient);
                AllUsers.user.put(patientData[0], patientData[1]);
                MedicalRecord medicalRecord = new MedicalRecord(patientData[0], patientData[3], patientData[4], patientData[5], patientData[8], patientData[6], patientData[7]);
                MedicalRecord.patientRecordMap.put(patientData[0], medicalRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Menu: 1. Login 2. Shut down");
        Scanner sc = new Scanner(System.in);
        int choice;
        
        do{ choice = sc.nextInt();
            String thisUserId= AllUsers.login();
            try (BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(staffListName)))) {
                if (thisUserId.charAt(0) != 'P' && thisUserId.charAt(1) != '1'){
                    String line2;
                    while ((line2 = br2.readLine()) != null){
                        String[] userData1 = line2.split("\\|");
                        if (userData1[1] == "password" ) AllUsers.changeUserPassword(thisUserId);
                        if (userData1[0].equals(thisUserId)){
                            String role = userData1[6]; 
        
                            switch(role){
                                case "Doctor":
                                    Doctor doctor = new Doctor(thisUserId, userData1[1], userData1[6], userData1[2]);
                                    doctor.displayMenu();
                                    break;
                                case "Administrator":
                                    Administrator administrator = new Administrator(thisUserId, userData1[1]);
                                    administrator.displayMenu();
                                    break;
                                case "Pharmacist":
                                    Pharmacist pharmacist = new Pharmacist(thisUserId, role, null);
                                    pharmacist.DisplayMenu();
                                    break;
                                default:
                                    System.out.println("nah bro");
                                    break;
                            }
                        }
                    }
                }
            
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (BufferedReader br4 = new BufferedReader(new InputStreamReader(new FileInputStream(patientListName)))) {
                String line4;
                while ((line4 = br4.readLine()) != null){
                    String[] patientData1 = line4.split("\\|");
                    if (patientData1[1].equals( "password" )) {AllUsers.changeUserPassword(thisUserId);}
                    if (patientData1[0].equals(thisUserId)){
                        Patients patient = new Patients(patientData1[0], patientData1[1], patientData1[2], patientData1[3], patientData1[4], patientData1[5], patientData1[6], patientData1[7], patientData1[8]);
                        patient.displayMenu();
                        return;
                    }
                } 
            }catch (IOException e) {
                    e.printStackTrace();
                }
    } while (choice == 1);

    }
}
