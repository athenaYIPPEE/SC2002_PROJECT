package labproject;

public class doctorScheduleService {

	private List<String> availableSlots;
	
	public doctorScheduleService() {
		this.availableSlots = new ArrayList<>();
	}
	
	public void viewPersonalScehdule() {
		System.out.println("Upcoming Appointments:");
        if (appointments.isEmpty()) {
            System.out.println("No upcoming appointments.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment);  // Display each appointment
            }
        }
	}
	
	public void setAvailability() {
		System.out.println("Available Slots: ");
        if (slots.isEmpty()) {System.out.println("No available slots.");
        else {
        	for (String avail: slots) {
        		System.out.println(slots);
        	}
        }
	
}