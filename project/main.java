package project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\athen\\OneDrive\\Documents\\GitHub\\SC2002_PROJECT\\project\\doctor_data.txt"; // specify your file path
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            String name = "";
            String hospitalId = "";
            String password = "";
            String role = "";
            List<LocalDateTime> availability = new ArrayList<>();

            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Doctor: ")) {
                    name = line.substring("Doctor: ".length()).trim();
                } else if (line.startsWith("HospitalId=")) {
                    hospitalId = line.substring("HospitalId=".length()).trim();
                } else if (line.startsWith("Password=")) {
                    password = line.substring("Password=".length()).trim();
                } else if (line.startsWith("Role=")) {
                    role = line.substring("Role=".length()).trim();
                } else if (line.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}")) { // Availability format
                    availability.add(LocalDateTime.parse(line.trim(), formatter));
                }
            }

            // Create a new Doctor object with the read data
            Doctor doctor = new Doctor(hospitalId, password, role, name);
            doctor.setAvailability(new java.util.Date(), availability);  // Example using today's date
            System.out.println("Doctor created: " + doctor.getName());

            
            while (1) {
            doctor.displayMenu();}

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
