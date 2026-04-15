import java.util.ArrayList;
import java.io.*;

public class Library {

    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private final String FILE_NAME = "books.txt";

    public Library() {
        loadBooksFromFile();
    }

    private void loadBooksFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            int maxId = 100;
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String bookId = data[0];
                String title = data[1];
                String author = data[2];
                boolean isAvailable = Boolean.parseBoolean(data[3]);

                Book book = new Book(title, author);
                book.setAvailable(isAvailable);

                books.add(book);

                int idNum = Integer.parseInt(bookId.substring(1));
                if (idNum > maxId) {
                    maxId = idNum;
                }
            }

            Book.setCounter(maxId + 1);

        } catch (IOException e) {
            System.out.println("No existing data found. Starting fresh.");
        }
    }

    private void saveBooksToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Book b : books) {
                bw.write(b.getBookId() + "," +
                        b.getTitle() + "," +
                        b.getAuthor() + "," +
                        b.isAvailable());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    public void addBook(String title, String author) {
        Book book = new Book(title, author);
        books.add(book);
        saveBooksToFile();
        System.out.println("Book added successfully!");
        System.out.println("Book ID: " + book.getBookId());
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book b : books) {
            b.displayBook();
        }
    }

    public void removeBook(String bookId) {
        for (Book b : books) {
            if (b.getBookId().equals(bookId)) {
                books.remove(b);
                saveBooksToFile();
                System.out.println("Book removed successfully!");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void updateBook(String bookId, String newTitle, String newAuthor) {
        for (Book b : books) {
            if (b.getBookId().equals(bookId)) {
                b.setTitle(newTitle);
                b.setAuthor(newAuthor);
                saveBooksToFile();
                System.out.println("Book updated successfully!");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void searchBook(String keyword) {
        boolean found = false;

        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                b.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {

                b.displayBook();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found.");
        }
    }


    public User registerUser(String name, String password) {
        User user = new User(name, password);
        users.add(user);
        System.out.println("User registered successfully!");
        System.out.println("Your User ID: " + user.getUserId());
        return user;
    }

    public User loginUser(String userId, String password) {
        for (User u : users) {
            if (u.getUserId().equals(userId) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public void viewUsers() {
        for (User u : users) {
            u.displayUser();
        }
    }

    public void issueBook(String userId, String bookId) {
        Book book = null;

        for (Book b : books) {
            if (b.getBookId().equals(bookId)) {
                book = b;
                break;
            }
        }

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is already issued.");
            return;
        }

        Transaction t = new Transaction(userId, bookId);
        transactions.add(t);
        book.setAvailable(false);

        System.out.println("Book issued successfully!");
    }

    public void returnBook(String userId, String bookId) {
        for (Transaction t : transactions) {
            if (t.getUserId().equals(userId) &&
                t.getBookId().equals(bookId) &&
                t.getReturnDate() == null) {

                t.returnBook();

                for (Book b : books) {
                    if (b.getBookId().equals(bookId)) {
                        b.setAvailable(true);
                        break;
                    }
                }

                System.out.println("Book returned successfully!");
                t.displayTransaction();
                return;
            }
        }

        System.out.println("No active transaction found.");
    }

    public void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        for (Transaction t : transactions) {
            t.displayTransaction();
        }
    }

    public void viewUserBooks(String userId) {
        boolean found = false;

        for (Transaction t : transactions) {
            if (t.getUserId().equals(userId) && t.getReturnDate() == null) {
                System.out.println("Book ID: " + t.getBookId());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books issued.");
        }
    }
}