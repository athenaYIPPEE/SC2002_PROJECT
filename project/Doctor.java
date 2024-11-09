package project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Doctor extends AllUsers{

	private static String name;
		private HashMap<String, Patients> patientsMap;
		protected List<Appointment> appointments; //list of confirmed appts
		private HashMap<Date, AppointmentSlots> personalSchedule; // personal schedule hashmap
		protected List<LocalDateTime> appointmentSlots; //list of avail appts
		protected static List<String> doctorNames = new ArrayList<>();
		protected static List<Doctor> doctors = new ArrayList<>();

		private DoctorMedicalRecord doctorMedicalRecord;
		private DoctorScheduleService doctorScheduleService;
		private DoctorAppointmentService doctorAppointmentService;
		
		public Doctor(String hospitalId, String password, String role, String name) {
			super(hospitalId, password, role);
			this.patientsMap = new HashMap<>();
			this.appointments = new ArrayList<>();
			this.name = name;
			this.appointmentSlots = new ArrayList<>();
			doctors.add(this);
			doctorNames.add(name);
		}
	
		public static String getName() {
			return name;
		}

		// Add availability for this doctor
		public void setAvailability(Date date, List<LocalDateTime> availableSlots) {
			AppointmentSlots appointmentSlots = new AppointmentSlots(name);
			for (LocalDateTime slot : availableSlots) {
				appointmentSlots.addSlot(slot);
			}
			personalSchedule.put(date, appointmentSlots);
		}

		// Get this doctor's AppointmentSlots for a given date
		public AppointmentSlots getAppointmentSlots() {
			// Retrieve the appointment slots for today's date (or a given date)
			Date today = new Date(); // Today's date, for example
			return personalSchedule.get(today); // Return the slots for today or specific date
		}

		public void addAppointment(Appointment appointment) {
			this.appointments.add(appointment); // Add the appointment to the doctor's list
		}

		public List<Appointment> getAppointments() {
			return appointments;  // Return the list of appointments
		}

	
	public void displayMenu() {
		System.out.println("Menu: \n"
				+ "1: View Patient Medical Records \n"
				+ "2: Update Patient Medical Records \n"
				+ "3: View Personal Schedule \n"
				+ "4: Set Availability for Appointments \n"
				+ "5: Accept or Decline Appointment Requests \n"
				+ "6: View Upcoming Appointments \n"
				+ "7: Record Appointment Outcome \n"
				+ "8: Logout");
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();
		
		switch(option) {
			case 1 -> doctorMedicalRecord.viewMedicalRecords();
			case 2 -> doctorMedicalRecord.updateMedicalRecords();
			case 3 -> doctorScheduleService.viewPersonalSchedule();
			case 4 -> doctorScheduleService.setAvailability();
			case 5 -> doctorAppointmentService.appointmentRequest();
			case 6 -> doctorAppointmentService.viewUpcomingAppointments();
			case 7 -> doctorAppointmentService.recordAppointmentOutcome();
			case 8 -> {
				System.out.println("Logging out...");
				return; // Exit the loop and terminate the method
			}
			default -> System.out.println("Invalid option. Please try again."); 
		}
	}	
}
