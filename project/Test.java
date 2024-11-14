package project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Test {
    public static void Run(){
        String staffListName = "C:\\Users\\athen\\OneDrive\\Documents\\GitHub\\SC2002_PROJECT\\project\\StaffList.txt"; // specify your file path
        String patientListName = "C:\\Users\\athen\\OneDrive\\Documents\\GitHub\\SC2002_PROJECT\\project\\PatientList.txt"; // specify your file path
        int choice;
        do{
        System.out.println("Menu: 1. Login 2. Shut down");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
            switch(choice){
                case 1->{
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

                    }
                } 
            }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        
            case 2 ->System.out.println("Shut down");
            
            default-> System.out.println("Shut down");
    } 
    }while (choice != 2);
    
        }
    }

