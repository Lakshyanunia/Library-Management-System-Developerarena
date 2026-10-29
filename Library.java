import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Library {
    private static final int LOAN_PERIOD_DAYS = 14;
    private final List<Book> books = new ArrayList<Book>();
    private final List<Member> members = new ArrayList<Member>();

    public boolean addBook(Book book) {
        Objects.requireNonNull(book, "Book cannot be null.");
        if (findBookByIsbn(book.getIsbn()) != null) {
            return false;
        }
        books.add(book);
        return true;
    }

    public boolean addMember(Member member) {
        Objects.requireNonNull(member, "Member cannot be null.");
        if (findMemberById(member.getMemberId()) != null) {
            return false;
        }
        members.add(member);
        return true;
    }

    public Book findBookByIsbn(String isbn) {
        if (isbn == null) {
            return null;
        }
        String normalizedIsbn = isbn.trim();
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(normalizedIsbn)) {
                return book;
            }
        }
        return null;
    }

    public Member findMemberById(String memberId) {
        if (memberId == null) {
            return null;
        }
        String normalizedId = memberId.trim();
        for (Member member : members) {
            if (member.getMemberId().equalsIgnoreCase(normalizedId)) {
                return member;
            }
        }
        return null;
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> results = new ArrayList<Book>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }

        String normalizedKeyword = keyword.trim().toLowerCase(Locale.ROOT);
        for (Book book : books) {
            if (book.getIsbn().toLowerCase(Locale.ROOT).contains(normalizedKeyword)
                    || book.getTitle().toLowerCase(Locale.ROOT).contains(normalizedKeyword)
                    || book.getAuthor().toLowerCase(Locale.ROOT).contains(normalizedKeyword)
                    || book.getGenre().toLowerCase(Locale.ROOT).contains(normalizedKeyword)) {
                results.add(book);
            }
        }
        return results;
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<Book>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return Collections.unmodifiableList(availableBooks);
    }

    public LocalDate borrowBook(String memberId, String isbn) {
        Member member = findMemberById(memberId);
        Book book = findBookByIsbn(isbn);
        if (member == null || book == null || !books.contains(book)) {
            return null;
        }

        LocalDate dueDate = LocalDate.now().plusDays(LOAN_PERIOD_DAYS);
        return member.borrowBook(book, dueDate) ? dueDate : null;
    }

    public boolean returnBook(String memberId, String isbn) {
        Member member = findMemberById(memberId);
        Book book = findBookByIsbn(isbn);
        return member != null && book != null && books.contains(book) && member.returnBook(book);
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(new ArrayList<Book>(books));
    }

    public List<Member> getMembers() {
        return Collections.unmodifiableList(new ArrayList<Member>(members));
    }
}
