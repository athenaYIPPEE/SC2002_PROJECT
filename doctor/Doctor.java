package labproject;

import java.util.HashMap;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends all_users{

	private HashMap<String, Patient> patientsMap;
	private List<Appointment> appointments; //appointment objects
	private List<String> slots;
	
	public Doctor(String hospitalId, String password, String role) {
		super(hospitalId, password, role);
		this.patientsMap = new HashMap<>();
		this.appointments = new ArrayList<>();
        	this.slots = new ArrayList<>();
	}
	
	public void displaymenu() {
		System.out.println("Menu: \n"
				+ "1: View Patient Medical Records \n"
				+ "2: Update Patient Medical Records \n"
				+ "3: View Personal Schedule \n"
				+ "4: Set Availability for Appointments \n"
				+ "5: Accept or Decline Appointment Requests \n"
				+ "6: View Upcoming Appointments \n"
				+ "7: Record Appointment Outcome");
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();
		switch(option) {
			case 1 -> doctorMedicalRecord.viewMedicalRecords();
			case 2 -> doctorMedicalRecord.updateMedicalRecords();
			case 3 -> doctorScheduleService.viewPersonalSchedule();
			case 4 -> doctorScheduleService.setAvailability();
			case 5 -> doctorAppointmentService.AppointmentRequest();
			case 6 -> doctorAppointmentService.viewUpcomingAppointments();
			case 7 -> doctorAppointmentService.recordAppointmentOutcome();
			default -> System.out.println("Invalid option. Please try again."); 
		}
	}	
}
