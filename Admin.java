import java.util.Scanner;

public class Admin {

    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "1234";

    public boolean login(String username, String password) {
        return username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD);
    }

    public void adminMenu(Library library) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Update Book");
            System.out.println("4. View All Books");
            System.out.println("5. View Users");
            System.out.println("6. View Transactions");
            System.out.println("7. Logout");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter author: ");
                    String author = sc.nextLine();

                    library.addBook(title, author);
                    break;

                case 2:
                    System.out.print("Enter Book ID to remove: ");
                    String removeId = sc.nextLine();

                    library.removeBook(removeId);
                    break;

                case 3:
                    System.out.print("Enter Book ID to update: ");
                    String updateId = sc.nextLine();

                    System.out.print("Enter new title: ");
                    String newTitle = sc.nextLine();

                    System.out.print("Enter new author: ");
                    String newAuthor = sc.nextLine();

                    library.updateBook(updateId, newTitle, newAuthor);
                    break;

                case 4:
                    library.viewBooks();
                    break;

                case 5:
                    library.viewUsers();
                    break;

                case 6:
                    library.viewTransactions();
                    break;

                case 7:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 7);
    }
}