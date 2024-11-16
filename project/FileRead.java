package project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileRead {
    public static void helppls(){
        String staffListName = "C:\\Users\\prisc\\OneDrive\\Documents\\GitHub\\SC2002_PROJECT\\project\\StaffList.txt"; // specify your file path
            String patientListName = "C:\\Users\\prisc\\OneDrive\\Documents\\GitHub\\SC2002_PROJECT\\project\\PatientList.txt"; // specify your file path
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
                    PatientsContactInfo contactInfo = new PatientsContactInfo(patientData[6], patientData[7]);
                    PatientsContactInfo.contactInfoMap.put(patientData[0], contactInfo);
                    MedicalRecord medicalRecord = new MedicalRecord(patientData[0], patientData[3], patientData[4], patientData[5], patientData[8], patientData[6], patientData[7]);
                    MedicalRecord.patientRecordMap.put(patientData[0], medicalRecord);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    
}

