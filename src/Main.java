import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserManagement userManagement = new UserManagement();

        while (true) {
            System.out.println("1. Create User");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. Display Users");
            System.out.println("5. Add Carbon Consumption");
            System.out.println("6. Display User Total Consumptions");
            System.out.println("7. Display User Daily Report");
            System.out.println("8. Display All Users and their carbon consumptions ");
            System.out.println("9. Display User consumptions records  ");
            System.out.println("10. Display Weekly Report  ");
            System.out.println("11. Display Monthly Report  ");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter username : ");
                    String name = scanner.nextLine();
                    System.out.println("Enter age : ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter ID : ");
                    String id = scanner.nextLine();
                    userManagement.createUser(name, age, id);
                    break;
                case 2:
                    System.out.print("Enter user ID to modify: ");
                    id = scanner.nextLine();
                    System.out.println("Enter new username : ");
                    name = scanner.nextLine();
                    System.out.println("Enter new age : ");
                    age = scanner.nextInt();
                    scanner.nextLine();
                    userManagement.editUser(id, name, age);
                    break;
                case 3:
                    System.out.print("Enter user ID to delete: ");
                    id = scanner.nextLine();
                    userManagement.deleteUser(id);
                    break;
                case 4:
                    userManagement.displayUsers();
                    break;
                case 5:
                    System.out.print("Enter user ID: ");
                    id = scanner.nextLine();
                    System.out.print("Enter carbon consumption (kg CO2): ");
                    double consumption = scanner.nextDouble();
                    scanner.nextLine();
                    userManagement.addConsumption(id, consumption);
                    break;
                case 6:
                    System.out.print("Enter user ID: ");
                    id = scanner.nextLine();
                    userManagement.displayUserConsumptions(id);
                    break;
                case 7:
                    System.out.print("Enter user ID: ");
                    id = scanner.nextLine();
                    userManagement.displayUserDailyReport(id);
                    break;
                case 8:
                    userManagement.displayAllUsersConsumptions();
                    break;
                case 9:
                    System.out.print("Enter user ID: ");
                    id = scanner.nextLine();
                    userManagement.displayUserConsumptionsRecords(id);
                    break;
                case 10:
                    System.out.print("Enter user ID: ");
                    id = scanner.nextLine();
                    userManagement.displayUserWeeklyReport(id);
                    break;
                case 11:
                    System.out.print("Enter user ID: ");
                    id = scanner.nextLine();
                    userManagement.displayUserMonthlyReport(id);
                    break;
                case 12:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
