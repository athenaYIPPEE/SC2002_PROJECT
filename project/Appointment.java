package project;

import java.time.LocalDateTime;
import java.util.*;

public class Appointment {
    private String appointmentId;
    private String doctorName;
    private String patientId;
    private LocalDateTime appointmentTime;
    private String status; // confirmed, canceled, completed, etc.
    private String serviceType;
    private Medication prescribedMedications; 
    protected ArrayList<Medication> prescribedMedicationList;
        private String consultationNotes;
        public static List<Appointment> allAppointments = new ArrayList<>();
            
                // Constructor
                public Appointment(String appointmentId, String doctorName, String patientId, LocalDateTime appointmentTime, String status, String serviceType) {
                    this.appointmentId = appointmentId;
                    this.doctorName = doctorName;
                    this.patientId = patientId;
                    this.appointmentTime = appointmentTime;
                    this.status = status;
                    this.serviceType = serviceType;
                    this.prescribedMedicationList = new ArrayList<>();
                    allAppointments.add(this);
                }
            
                // Getters and Setters
                public String getAppointmentId() {
                    return appointmentId;
                }
            
                public String getDoctorName() {
                    return doctorName;
                }
            
                public String getPatientId() {
                    return patientId;
                }
            
                public LocalDateTime getAppointmentDate() {
                    return appointmentTime;
                }
            
                public void setAppointmentDateTime(LocalDateTime appointmentDate) {
                    this.appointmentTime= appointmentDate;
                }
            
                public String getStatus() {
                    return status;
                }
            
                public String getServiceType(){
                    return serviceType;
                }
            
                public void setStatus(String status) {
                    this.status = status;
                }
            
                public String getConsultationNotes(){
                    return consultationNotes;
                }
            
                @Override
                public String toString() {
                    return "Appointment [Appointment ID: " + appointmentId + ", Doctor: " + doctorName + 
                            ", Patient ID: " + patientId + ", Date: " + appointmentTime + 
                             ", Status: " + status + "]";
                }
            
                public void setServiceType(String serviceType){
                    this.serviceType = serviceType;
                }
                
                public void addMedication(Medication medication) {
                    prescribedMedicationList.add(medication);
                } 
                
                public void recordConsultationNotes(String notes) {
                    if (this.consultationNotes.isEmpty()) {
                        this.consultationNotes = notes; // If no existing notes, set directly
                    } else {
                        this.consultationNotes += "\n" + notes; // Append new notes with a newline
                    }
                }
            
                public void getOutcome() {
                    // Print appointment date and service type
                    System.out.println(getAppointmentDate());
                    System.out.println(getServiceType());
                    
                    // Print each prescribed medication in the list
                    for (int i = 0; i < prescribedMedicationList.size(); i++) {
                        System.out.println(prescribedMedicationList.get(i));  // Corrected this line
                    }
                    
                    // Print consultation notes
                    System.out.println(getConsultationNotes());
                }
            
                public static List<Appointment> getAllAppointments() {
                    return allAppointments;
        }
    
        public ArrayList<Medication> getPrescribedMedicationList() {
            return prescribedMedicationList;
    }
}
