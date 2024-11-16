package project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Doctor extends AllUsers{

	private String name;
		private HashMap<String, Patients> patientsMap;
		protected List<Appointment> appointments; 
		private static HashMap<LocalDate, AppointmentSlots> personalSchedule; // personal schedule hashmap
		protected List<LocalDateTime> appointmentSlots; //list of avail appts
		protected static List<String> doctorNames = new ArrayList<>();
		public static List<Doctor> doctors = new ArrayList<>();

		private DoctorMedicalRecord doctorMedicalRecord;
		private DoctorScheduleService doctorScheduleService;
		private DoctorAppointmentService doctorAppointmentService;
		
		public Doctor(String hospitalId, String password, String role, String name) {
			super(hospitalId, password, role);
			this.patientsMap = new HashMap<>();
			this.appointments = new ArrayList<>();
			this.name = name;
			this.appointmentSlots = new ArrayList<>();
			this.personalSchedule = new HashMap<>();
			this.doctorScheduleService = new DoctorScheduleService(this);  
			this.doctorAppointmentService = new DoctorAppointmentService(this);
			doctors.add(this);
			doctorNames.add(name);
		}
	
		public String getName() {
			return name;
		}

		// Add availability for this doctor
		public void setAvailability(LocalDate date, List<LocalDateTime> availableSlots) {
			AppointmentSlots appointmentSlots = new AppointmentSlots(name);
			for (LocalDateTime slot : availableSlots) {
				appointmentSlots.addSlot(slot);
			}
			personalSchedule.put(date, appointmentSlots);
		}

		// Get this doctor's AppointmentSlots for a given date
		public AppointmentSlots getAppointmentSlots() {
			// Retrieve the appointment slots for today's date (or a given date)
			LocalDate today = LocalDate.now(); //cannot use 'new Date();' bcos the java library constructor giives up to millisecond precision which isnt what we want // Today's date, for example
			return personalSchedule.get(today); // Return the slots for today or specific date
		}

		public List<LocalDateTime> returnAppointmentSlots(){
			return appointmentSlots;
		}

		public void addAppointment(Appointment appointment) {
			this.appointments.add(appointment); // Add the appointment to the doctor's list
		}

		public List<Appointment> getAppointments() {
			return appointments;  // Return the list of appointments
		}

		public void setPersonalSchedule(HashMap<LocalDate, AppointmentSlots> schedule){
			this.personalSchedule = schedule;
		}

	
		public void displayMenu(String doctorId) {
			int option; // Declare the option variable before the loop to be used inside

			int curDoc = 0;
			for (Doctor doctor : doctors) {
				if (doctor.getHospitalId().equalsIgnoreCase(doctorId)) {
					break;
				}
				curDoc++;
			}

			do {
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
				option = sc.nextInt(); // Take user input for menu option
		
				switch(option) {
					case 1 -> doctors.get(curDoc).doctorMedicalRecord.viewMedicalRecords();
					case 2 -> doctors.get(curDoc).doctorMedicalRecord.updateMedicalRecords();
					case 3 -> doctors.get(curDoc).doctorScheduleService.viewPersonalSchedule();
					case 4 -> doctors.get(curDoc).doctorScheduleService.setAvailability();
					case 5 -> doctors.get(curDoc).doctorAppointmentService.appointmentRequest();
					case 6 -> doctors.get(curDoc).doctorAppointmentService.viewUpcomingAppointments();
					case 7 -> doctors.get(curDoc).doctorAppointmentService.recordAppointmentOutcome();
					case 8 -> {
						System.out.println("Logging out...");
						return; // Exit the loop and terminate the method when the user logs out
					}
					default -> System.out.println("Invalid option. Please try again."); 
				}
			} while (option != 8); // Continue looping until the user selects option 8 to log out
		}		
}
