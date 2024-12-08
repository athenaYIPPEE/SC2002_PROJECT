package project;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    private Integer bill = 0;
            
                // Constructor
                public Appointment(String appointmentId, String doctorName, String patientId, LocalDateTime appointmentTime, String status, String serviceType) {
                    this.appointmentId = appointmentId;
                    this.doctorName = doctorName;
                    this.patientId = patientId;
                    this.appointmentTime = appointmentTime;
                    this.status = status;
                    this.serviceType = serviceType;
                    this.prescribedMedicationList = new ArrayList<>();
                    this.consultationNotes = "";
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
                    this.appointmentTime = appointmentDate.truncatedTo(ChronoUnit.MINUTES);
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

                public Integer getBill(){
                    return bill;
                }

                public void setBill(String serviceTypeStr){
                    ServiceType serviceEnum;
                    try {
                        serviceEnum = ServiceType.valueOf(serviceTypeStr.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid service type: " + serviceTypeStr);
                        return;
                    }

                    if (serviceEnum == ServiceType.CONSULTATION) {
                        this.bill = 25;
                    } else if (serviceEnum == ServiceType.XRAY) {
                        this.bill = 200;
                    } else if (serviceEnum == ServiceType.BLOOD_TEST) {
                        this.bill = 50;
                    } else if (serviceEnum == ServiceType.SURGERY) {
                        this.bill = 50000;
                    } else if (serviceEnum == ServiceType.OTHER) {
                        this.bill = 250;
                    }
                }
            
                @Override
                public String toString() {
                    return "Appointment [Appointment ID: " + appointmentId + ", Doctor: " + doctorName + 
                            ", Patient ID: " + patientId + ", Date: " + appointmentTime + ", Service Type: " + serviceType +
                             ", Status: " + status + "]";
                }
            
                public void setServiceType(String serviceType){
                    this.serviceType = serviceType;
                }
                
                public void addMedication(Medication medication) {
                    prescribedMedicationList.add(medication);
                } 
                
                public void recordConsultationNotes(String notes) {
                    /*if (this.consultationNotes.isEmpty()) {
                        this.consultationNotes = notes; // If no existing notes, set directly
                    } else {
                        this.consultationNotes += "\n" + notes; // Append new notes with a newline
                    }*/
                    if (!notes.equals("exit")) this.consultationNotes += "\n" + notes;
                }
            
                public void getOutcome(Appointment appointment) {
                    // Print appointment date and service type
                    System.out.println("Appointment ID: " + appointment.getAppointmentId());
                    System.out.println("Date: " + appointment.getAppointmentDate());
                    System.out.println("Service Type: " + appointment.getServiceType());
                    
                    // Print each prescribed medication in the list
                    for (int i = 0; i < appointment.prescribedMedicationList.size(); i++) {
                        System.out.println(appointment.prescribedMedicationList.get(i));  // Corrected this line
                    }
                    
                    // Print consultation notes
                    System.out.println("Consultation Notes: " + appointment.getConsultationNotes());

                    appointment.setBill(appointment.getServiceType());
                    System.out.println("Billing cost of appointment: $" + appointment.getBill());
                }
            
                public static List<Appointment> getAllAppointments() {
                    return allAppointments;
        }
    
        public ArrayList<Medication> getPrescribedMedicationList() {
            return prescribedMedicationList;
    }
}
