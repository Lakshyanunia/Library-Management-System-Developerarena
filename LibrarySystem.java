import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class LibrarySystem {
    private final Library library;
    private final Scanner scanner;

    public LibrarySystem() {
        library = new Library();
        scanner = new Scanner(System.in);
        addSampleData();
    }

    public static void main(String[] args) {
        new LibrarySystem().run();
    }

    private void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            String choice = prompt("Enter your choice: ");
            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    addMember();
                    break;
                case "3":
                    displayBooks(library.getBooks(), "ALL BOOKS");
                    break;
                case "4":
                    displayBooks(library.getAvailableBooks(), "AVAILABLE BOOKS");
                    break;
                case "5":
                    searchBooks();
                    break;
                case "6":
                    borrowBook();
                    break;
                case "7":
                    returnBook();
                    break;
                case "8":
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid option. Please choose from the menu.");
            }
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
        System.out.println("1. Add New Book");
        System.out.println("2. Register New Member");
        System.out.println("3. Display All Books");
        System.out.println("4. Display Available Books");
        System.out.println("5. Search Books");
        System.out.println("6. Borrow Book");
        System.out.println("7. Return Book");
        System.out.println("8. Exit");
    }

    private void addBook() {
        System.out.println("\n=== ADD NEW BOOK ===");
        try {
            String isbn = prompt("Enter ISBN: ");
            String title = prompt("Enter Title: ");
            String author = prompt("Enter Author: ");
            String genre = prompt("Enter Genre: ");
            if (library.addBook(new Book(isbn, title, author, genre))) {
                System.out.println("Book added successfully!");
            } else {
                System.out.println("A book with that ISBN already exists.");
            }
        } catch (IllegalArgumentException exception) {
            System.out.println("Could not add book: " + exception.getMessage());
        }
    }

    private void searchBooks() {
        System.out.println("\n=== SEARCH BOOKS ===");
        String keyword = prompt("Enter search keyword: ");
        System.out.println("\nSearch Results:");
        List<Book> results = library.searchBooks(keyword);
        if (results.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        for (Book book : results) {
            book.displayInfo();
        }
    }

    private void displayBooks(List<Book> books, String heading) {
        System.out.println("\n=== " + heading + " ===");
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        for (Book book : books) {
            book.displayInfo();
        }
    }

    private void addMember() {
        System.out.println("\n=== REGISTER NEW MEMBER ===");
        try {
            String memberId = prompt("Enter Member ID: ");
            String name = prompt("Enter Name: ");
            String contact = prompt("Enter Contact: ");
            if (library.addMember(new Member(memberId, name, contact))) {
                System.out.println("Member registered successfully!");
            } else {
                System.out.println("A member with that ID already exists.");
            }
        } catch (IllegalArgumentException exception) {
            System.out.println("Could not add member: " + exception.getMessage());
        }
    }

    private void borrowBook() {
        System.out.println("\n=== BORROW BOOK ===");
        String memberId = prompt("Enter Member ID: ");
        Member member = library.findMemberById(memberId);
        if (member == null) {
            System.out.println("No member found with that ID.");
            return;
        }

        String isbn = prompt("Enter Book ISBN: ");
        Book book = library.findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("No book found with that ISBN.");
            return;
        }
        if (!book.isAvailable()) {
            System.out.println("That book is already borrowed.");
            return;
        }

        LocalDate dueDate = library.borrowBook(memberId, isbn);
        if (dueDate == null) {
            System.out.println("Could not borrow the book.");
            return;
        }
        System.out.println("Book borrowed successfully!");
        System.out.println("Member: " + member.getName());
        System.out.println("Book: " + book.getTitle());
        System.out.println("Due Date: " + dueDate);
    }

    private void returnBook() {
        System.out.println("\n=== RETURN BOOK ===");
        String memberId = prompt("Enter Member ID: ");
        String isbn = prompt("Enter Book ISBN: ");
        if (library.returnBook(memberId, isbn)) {
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Could not return book. Check the member ID, ISBN, and that the member borrowed this book.");
        }
    }

    private String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private void addSampleData() {
        library.addBook(new Book("978-0-262-03384-8", "Introduction to Algorithms", "Thomas Cormen", "Computer Science"));
        library.addBook(new Book("978-0-13-468599-1", "Effective Java", "Joshua Bloch", "Programming"));
        library.addBook(new Book("978-1-56619-909-4", "Clean Code", "Robert Martin", "Programming"));
        library.addMember(new Member("M001", "Alice Johnson", "alice@example.com"));
        library.addMember(new Member("M002", "Sam Lee", "sam@example.com"));
        library.borrowBook("M002", "978-0-13-468599-1");
    }
}
