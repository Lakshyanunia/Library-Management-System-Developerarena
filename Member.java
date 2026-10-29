import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {
    private final String memberId;
    private String name;
    private String contact;
    private final List<Book> borrowedBooks = new ArrayList<Book>();

    public Member(String memberId, String name, String contact) {
        this.memberId = requireText(memberId, "Member ID");
        setName(name);
        setContact(contact);
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = requireText(name, "Name");
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = requireText(contact, "Contact");
    }

    public List<Book> getBorrowedBooks() {
        return Collections.unmodifiableList(new ArrayList<Book>(borrowedBooks));
    }

    boolean borrowBook(Book book, LocalDate dueDate) {
        if (!book.borrowUntil(dueDate)) {
            return false;
        }
        borrowedBooks.add(book);
        return true;
    }

    boolean returnBook(Book book) {
        if (!borrowedBooks.remove(book)) {
            return false;
        }
        book.returnToLibrary();
        return true;
    }

    public void displayInfo() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Contact: " + contact);
        System.out.println("Books Borrowed: " + borrowedBooks.size());
        for (Book book : borrowedBooks) {
            System.out.println("  - " + book.getTitle() + " (due " + book.getDueDate() + ")");
        }
        System.out.println("----------------------------------------");
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }
}
