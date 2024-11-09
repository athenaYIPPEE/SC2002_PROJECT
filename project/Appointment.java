package project;

import java.time.LocalDate;
import java.util.*;

public class Appointment {
    private String appointmentId;
    private String doctorName;
    private String patientId;
    private LocalDate appointmentDate;
    private String timeSlot;
    private String status; // confirmed, canceled, completed, etc.
    private String serviceType;
    private Medication prescribedMedications; 
    protected ArrayList<Medication> prescribedMedicationList;
    private String consultationNotes;

    // Constructor
    public Appointment(String appointmentId, String doctorName, String patientId, LocalDate appointmentDate, String timeSlot, String status, String serviceType) {
        this.appointmentId = appointmentId;
        this.doctorName = doctorName;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.timeSlot = timeSlot;
        this.status = status;
        this.serviceType = serviceType;
        this.prescribedMedicationList = new ArrayList<>();
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

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment [Appointment ID: " + appointmentId + ", Doctor: " + doctorName + 
                ", Patient ID: " + patientId + ", Date: " + appointmentDate + 
                ", Time Slot: " + timeSlot + ", Status: " + status + "]";
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
}
