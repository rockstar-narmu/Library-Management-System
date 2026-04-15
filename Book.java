public class Book {
    private static int counter = 101;

    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.bookId = "B" + counter++;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public static void setCounter(int value) {
        counter = value;
    }

    public void displayBook() {
        System.out.println(bookId + " | " + title + " | " + author + " | " + (isAvailable ? "Available" : "Issued"));
    }
}