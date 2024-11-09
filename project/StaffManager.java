package project;

import java.util.Map;
import java.util.Scanner;

public class StaffManager extends AllUsers {
    public StaffManager(String hospitalId, String password, String role) {
        super(hospitalId, password, role);
    }

    public void addStaff() {
        System.out.print("Enter the Hospital ID of the new Staff: ");
        Scanner sc = new Scanner(System.in);
        String hospitalId = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter DOB (yyyy-mm-dd): ");
        String dob = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine(); // consume the newline
        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();
        System.out.print("Enter Role: ");
        String role = sc.nextLine();

        // Add to user and userInfoMap
        AllUsers.user.put(hospitalId, "password"); // Add default password
        AllUsers.userInfoMap.put(hospitalId, new StaffInfo(hospitalId, age, role, name, dob, gender));

        System.out.println("Staff added successfully.");
    }

    public void removeStaff() {
        System.out.print("Enter Hospital ID of the Staff to be removed: ");
        Scanner sc = new Scanner(System.in);
        String hospitalId = sc.nextLine();

        if (AllUsers.user.containsKey(hospitalId)) {
            AllUsers.user.remove(hospitalId);
            AllUsers.userInfoMap.remove(hospitalId); // Also remove from userInfoMap
            System.out.println("Staff with ID " + hospitalId + " removed from the system.");
        } else {
            System.out.println("No staff found with ID " + hospitalId);
        }
    }

    public static void displayStaff(String roleFilter, String genderFilter, int minAge, int maxAge) {
        System.out.println("Displaying staff with the following filters:");
        System.out.println("Role: " + (roleFilter != null ? roleFilter : "Any"));
        System.out.println("Gender: " + (genderFilter != null ? genderFilter : "Any"));
        System.out.println("Age Range: " + minAge + " - " + maxAge);

        boolean found = false; // Flag to check if any staff meets the criteria

        // Iterate through userInfoMap to display filtered staff
        for (Map.Entry<String, StaffInfo> entry : AllUsers.userInfoMap.entrySet()) {
            String hospitalId = entry.getKey();
            StaffInfo user = entry.getValue();

            // Filter check
            boolean matchesRole = (roleFilter == null || roleFilter.equals(user.getRole()));
            boolean matchesGender = (genderFilter == null || genderFilter.equals(user.getGender()));
            boolean matchesAge = (user.getAge() >= minAge && user.getAge() <= maxAge);

            // If the user matches all the filters, display their information
            if (matchesRole && matchesGender && matchesAge) {
                found = true;
                System.out.println("Staff ID: " + hospitalId);
                System.out.println("Name: " + user.getName());
                System.out.println("Role: " + user.getRole());
                System.out.println("Gender: " + user.getGender());
                System.out.println("Age: " + user.getAge());
                System.out.println("----------------------------");
            }
        }

        // If no staff matches the criteria
        if (!found) {
            System.out.println("No staff members found matching the provided criteria.");
        }
    }
}
