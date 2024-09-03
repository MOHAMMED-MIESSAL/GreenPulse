import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class UserManagement {

    private Map<String, User> users = new HashMap<>();

    // Create User function
    public void createUser(String name, int age, String id) {
        if (users.containsKey(id)) {
            System.out.println("Id " + id + " already exists");
        } else {
            User user = new User(name, age, id);
            users.put(id, user);
            System.out.println("User created successfully");
        }
    }

    // Edit User function
    public void editUser(String id, String newName, int newAge) {
        User user = users.get(id);
        if (user != null) {
            user.setName(newName);
            user.setAge(newAge);
            System.out.println("User updated successfully");
        } else {
            System.out.println("User not found!");
        }
    }

    // Delete User
    public void deleteUser(String id) {
        User user = users.get(id);
        if (user != null) {
            users.remove(id);
        } else {
            System.out.println("User not found!");
        }
    }

    // Display Users
    public void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("List of all users:");
            for (User user : users.values()) {
                System.out.println(user.displayInfosUsers());
            }
        }
    }

    // Add carbon consumption
    public void addConsumption(String id, double consumption) {
        User user = users.get(id);
        if (user != null) {
            Scanner scanner = new Scanner(System.in);

            // Start Date :
            System.out.println("Enter the Start date and time of carbon consumption (format: yyyy-MM-dd HH:mm): ");
            String startDateTimeInput = scanner.nextLine();
            // Define date and time format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            // End Date :
            System.out.println("Enter the End date and time of carbon consumption (format: yyyy-MM-dd HH:mm): ");
            String endDateTimeInput = scanner.nextLine();
            // Define date and time format
            DateTimeFormatter formatterEndDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


            try {
                // Parse dateTimeInput to LocalDateTime
                LocalDateTime StartDateTime = LocalDateTime.parse(startDateTimeInput, formatter);
                LocalDateTime EndDateTime = LocalDateTime.parse(endDateTimeInput, formatterEndDate);
                user.addConsumption(new CarbonConsumption(StartDateTime, EndDateTime, consumption));
                System.out.println("Consumption added successfully.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date and time format. Please try again.");
            }
        } else {
            System.out.println("User not found!");
        }
    }

    // Display User Total Consumption
    public void displayUserConsumptions(String id) {
        User user = users.get(id);
        if (user != null) {
            System.out.println(user.displayTotalConsumption());
        } else {
            System.out.println("User not found!");
        }
    }

    // Display the User with all records
    public void displayUserConsumptionsRecords(String id) {
        User user = users.get(id);
        if (user != null) {
            System.out.println(user.displayConsumption());
        } else {
            System.out.println("User not found!");
        }
    }

    // Display All Users with their consumptions
    public void displayAllUsersConsumptions() {
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("List of all users and their carbon consumptions:");
            for (User user : users.values()) {
                System.out.println(user.displayInfosUsers());
                System.out.println(user.displayTotalConsumption());
            }
        }
    }

    // Display Daily Report
    public void displayUserDailyReport(String id) {
        User user = users.get(id);
        if (user != null) {
            System.out.println(user.displayAverageDailyConsumption());
        } else {
            System.out.println("User not found!");
        }
    }
}
