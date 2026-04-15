import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Transaction {
    private String userId;
    private String bookId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;

    public Transaction(String userId, String bookId) {
        this.userId = userId;
        this.bookId = bookId;
        this.issueDate = LocalDate.now();
        this.dueDate = issueDate.plusDays(7);
        this.returnDate = null;
        this.fine = 0;
    }

    public void returnBook() {
        this.returnDate = LocalDate.now();

        if (returnDate.isAfter(dueDate)) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
            fine = daysLate * 5;
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void displayTransaction() {
        System.out.println("User: " + userId +
                " | Book: " + bookId +
                " | Issued: " + issueDate +
                " | Due: " + dueDate +
                " | Returned: " + (returnDate != null ? returnDate : "Not yet") +
                " | Fine: ₹" + fine);
    }
}