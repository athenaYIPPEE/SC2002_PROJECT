package project;

import java.util.Date;

public class Appointment {
    private String appointmentId;
    private String doctorName;
    private String patientId;
    private Date appointmentDate;
    private String timeSlot;
    private String status; // confirmed, canceled, completed, etc.
    private String serviceType;
    private List<Medication> prescribedMedications; 
    private String consultationNotes;

    // Constructor
    public Appointment(String appointmentId, String doctorName, String patientId, Date appointmentDate, String timeSlot, String status) {
        this.appointmentId = appointmentId;
        this.doctorName = doctorName;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.timeSlot = timeSlot;
        this.status = status;
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

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
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

    public void setServiceType(serviceType){
        this.serviceType = serviceType;
    }
    
    public void addMedication(Medication medication) {
        this.prescribedMedications.add(medication);
    }
    
    public void recordConsultationNotes(String notes) {
    	if (this.consultationNotes.isEmpty()) {
            this.consultationNotes = notes; // If no existing notes, set directly
        } else {
            this.consultationNotes += "\n" + notes; // Append new notes with a newline
        }
    }
}
