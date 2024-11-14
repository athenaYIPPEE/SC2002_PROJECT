package project;

import java.util.Scanner;

public class Test {
    public static void Run(){
        int choice;
        do{
        System.out.println("Menu: 1. Login 2. Shut down");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
            switch(choice){
                case 1->{
                    String thisUserId= AllUsers.login();
                    if (thisUserId.charAt(0) != 'P' && thisUserId.charAt(1) != '1'){
                        StaffInfo staffInfo = AllUsers.userInfoMap.get(thisUserId);
                        String role = staffInfo.getRole();
                        String password = AllUsers.user.get(thisUserId);
                        if (password.equals("password")) AllUsers.changeUserPassword(thisUserId);
                        switch(role){
                            case "Doctor":
                                Doctor doctor = new Doctor(thisUserId, password, role, staffInfo.getName());
                                doctor.displayMenu();
                                break;
                            case "Administrator":
                                Administrator administrator = new Administrator(thisUserId, password);
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
                    } else{
                        Patients patient = Patients.patientList.get(thisUserId);
                        String passwordPatient = AllUsers.user.get(thisUserId);
                        if (passwordPatient.equals("password")) AllUsers.changeUserPassword(thisUserId);
                        Patients thePatient = new Patients(thisUserId, AllUsers.user.get(thisUserId), "Patient", patient.getName(), patient.getDob(), patient.getGender(), patient.getPhone(), patient.getEmail(), patient.getBlood());
                        patient.displayMenu();
                    }

                    }
                    case 2 ->System.out.println("Shut down");
                    default-> System.out.println("Shut down");
                }

    }while (choice != 2);
    
        }
    }

