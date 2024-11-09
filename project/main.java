package project;

import java.io.*;

public class main {

    public static void main(String[] args) {
        String fileName = "C:\\Users\\athen\\OneDrive\\Documents\\GitHub\\SC2002_PROJECT\\project\\doctor_data.txt";
        
        // Sample data to write to the file
        String sampleData = "Doctor: John Doe\n"
                          + "HospitalId=H12345\n"
                          + "Password=securePassword\n"
                          + "Role=Doctor\n"
                          + "Availability:\n"
                          + "2024-11-09T09:00\n"
                          + "2024-11-09T10:00\n"
                          + "2024-11-09T11:00\n";

        // Write the sample data to the file
        writeToFile(fileName, sampleData);

        System.out.println("Data written to the file successfully.");
    }
    
    public static void writeToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
