package project;

import java.util.Scanner;

public class Test {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void Run(){
        int choice;
        System.out.println(ANSI_GREEN + "Welcome to our hospital system!" + ANSI_RESET);
        do{
        System.out.println("Menu: 1. Login 2. Shut down");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
            switch(choice){
                case 1->{
                    String thisUserId= AllUsers.login();
                    if (thisUserId.length() == 4){
                        StaffInfo staffInfo = AllUsers.userInfoMap.get(thisUserId);
                        String role = staffInfo.getRole();
                        String password = AllUsers.user.get(thisUserId);
                        if (password.equals("password")) AllUsers.changeUserPassword(thisUserId);
                        boolean found = false;
                        switch(role){
                            case "Doctor":
                                for (Doctor doctorInSystem : Doctor.doctors) {
                                    // Find the corresponding doctor from the list
                                    if (doctorInSystem.getHospitalId().equalsIgnoreCase(thisUserId)) {
                                        doctorInSystem.displayMenu(thisUserId);  // Directly call displayMenu on the correct doctor
                                        found = true;
                                    }
                                }
                                if (found == false)
                                {
                                    Doctor doctor = new Doctor(thisUserId, password, role, staffInfo.getName(), staffInfo.getAge(), staffInfo.getGender());
                                    doctor.displayMenu(thisUserId);
                                }
                                break;
                            case "Administrator":
                                for (Administrator adminInSystem : Administrator.admins) {
                                    // Find the corresponding doctor from the list
                                    if (adminInSystem.getHospitalId().equalsIgnoreCase(thisUserId)) {
                                        adminInSystem.displayMenu();  // Directly call displayMenu on the correct doctor
                                        found = true;
                                    }
                                }
                                if (found == false)
                                {
                                    Administrator administrator = new Administrator(thisUserId, password);
                                    administrator.displayMenu();
                                }
                                break;
                            case "Pharmacist":
                                for (Pharmacist pharmaInSystem : Pharmacist.pharmacists) {
                                    // Find the corresponding doctor from the list
                                    if (pharmaInSystem.getHospitalId().equalsIgnoreCase(thisUserId)) {
                                        pharmaInSystem.DisplayMenu();  // Directly call displayMenu on the correct doctor
                                        found = true;
                                    }
                                }
                                if (found == false)
                                {
                                    Pharmacist pharmacist = new Pharmacist(thisUserId, password, role, staffInfo.getName(), staffInfo.getAge(), staffInfo.getGender(), null);
                                    pharmacist.DisplayMenu();
                                }
                                break;
                            default:
                                System.out.println("nah bro");
                                break;
                        }
                    } else{
                        boolean found = false;
                        Patients patient = Patients.patientList.get(thisUserId);
                        String passwordPatient = AllUsers.user.get(thisUserId);
                        if (passwordPatient.equals("password")) AllUsers.changeUserPassword(thisUserId);
                        for (Patients patientInSystem : Patients.patients) {
                            // Find the corresponding doctor from the list
                            if (patientInSystem.getHospitalId().equalsIgnoreCase(thisUserId)) {
                                patientInSystem.displayMenu();  // Directly call displayMenu on the correct doctor
                                found = true;
                            }
                        }
                        if (found == false)
                        {
                            Patients thePatient = new Patients(thisUserId, AllUsers.user.get(thisUserId), "Patient", patient.getName(), patient.getDob(), patient.getGender(), patient.getPhone(), patient.getEmail(), patient.getBlood());
                            thePatient.displayMenu();
                        }
                    }
                }
                case 2 ->System.out.println(ANSI_GREEN + "Shut down. Thank you for using our system." + ANSI_RESET);
                default-> System.out.println(ANSI_GREEN + "Shut down" + ANSI_RESET);
            }
        } while (choice != 2);
    }
}

