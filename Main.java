import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        Admin admin = new Admin();

        int choice;

        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Admin Login");
            System.out.println("2. User Signup");
            System.out.println("3. User Login");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter username: ");
                    String username = sc.nextLine();

                    System.out.print("Enter password: ");
                    String password = sc.nextLine();

                    if (admin.login(username, password)) {
                        System.out.println("Admin login successful!");
                        admin.adminMenu(library);
                    } else {
                        System.out.println("Invalid credentials!");
                    }
                    break;

                case 2:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter password: ");
                    String userPass = sc.nextLine();

                    library.registerUser(name, userPass);
                    break;

                case 3:
                    System.out.print("Enter User ID: ");
                    String userId = sc.nextLine();

                    System.out.print("Enter password: ");
                    String pass = sc.nextLine();

                    User user = library.loginUser(userId, pass);

                    if (user != null) {
                        System.out.println("Login successful!");
                        userMenu(library, user);
                    } else {
                        System.out.println("Invalid User ID or Password!");
                    }
                    break;

                case 4:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

        sc.close();
    }

    public static void userMenu(Library library, User user) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- USER MENU ---");
            System.out.println("Welcome, " + user.getName());
            System.out.println("1. View Books");
            System.out.println("2. Search Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View My Books");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    library.viewBooks();
                    break;

                case 2:
                    System.out.print("Enter keyword: ");
                    String keyword = sc.nextLine();
                    library.searchBook(keyword);
                    break;

                case 3:
                    System.out.print("Enter Book ID: ");
                    String issueId = sc.nextLine();
                    library.issueBook(user.getUserId(), issueId);
                    break;

                case 4:
                    System.out.print("Enter Book ID: ");
                    String returnId = sc.nextLine();
                    library.returnBook(user.getUserId(), returnId);
                    break;

                case 5:
                    library.viewUserBooks(user.getUserId());
                    break;

                case 6:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }
}
