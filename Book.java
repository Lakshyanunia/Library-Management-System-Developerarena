import java.time.LocalDate;

public class Book {
    private final String isbn;
    private String title;
    private String author;
    private String genre;
    private LocalDate dueDate;

    public Book(String isbn, String title, String author, String genre) {
        this.isbn = requireText(isbn, "ISBN");
        setTitle(title);
        setAuthor(author);
        setGenre(genre);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = requireText(title, "Title");
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = requireText(author, "Author");
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = requireText(genre, "Genre");
    }

    public boolean isAvailable() {
        return dueDate == null;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    boolean borrowUntil(LocalDate dueDate) {
        if (!isAvailable()) {
            return false;
        }
        if (dueDate == null) {
            throw new IllegalArgumentException("Due date cannot be null.");
        }
        this.dueDate = dueDate;
        return true;
    }

    void returnToLibrary() {
        dueDate = null;
    }

    public void displayInfo() {
        System.out.println("ISBN: " + isbn);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Status: " + (isAvailable() ? "Available" : "Borrowed"));
        System.out.println("----------------------------------------");
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }
}
