# 📚 Library Management System

A console-based Library Management System developed using **Java**. This project simplifies basic library operations by allowing users to manage books and members, search for books, borrow books, and return borrowed books through an interactive menu.

## ✨ Features

- **Add New Books:** Register books using ISBN, title, author, and genre.
- **Member Registration:** Register library members with a unique member ID, name, and contact information.
- **Display All Books:** View all books registered in the library.
- **Available Books:** Display books that are currently available for borrowing.
- **Search Books:** Search for books using keywords matching the ISBN, title, author, or genre.
- **Borrow Books:** Allow registered members to borrow available books.
- **Return Books:** Return borrowed books and update their availability.
- **Due-Date Calculation:** Automatically calculate the due date as 14 days from the borrowing date.
- **Duplicate Prevention:** Prevent duplicate ISBNs and member IDs.
- **Input Validation:** Prevent empty values for required book and member fields.
- **Sample Data:** Load example books and members when the application starts.

## 🛠️ Technologies Used

- **Programming Language:** Java
- **IDE:** Visual Studio Code (VS Code)
- **Java Libraries:** `java.util`, `java.time`
- **Data Structures:** `ArrayList`, `List`
- **Concepts:** Object-Oriented Programming (OOP), encapsulation, collections, exception handling, and date handling
- **Storage:** In-memory storage

## 📁 Project Structure

```text
Library-Management-System/
├── Book.java
├── Library.java
├── LibrarySystem.java
├── Member.java
└── README.md
```

### Class Responsibilities

| Class | Description |
|---|---|
| `Book.java` | Stores book details, tracks availability, and manages due dates. |
| `Library.java` | Manages books and members, searches for books, and coordinates borrowing and returning. |
| `LibrarySystem.java` | Contains the main method, menu, user input, and console interface. |
| `Member.java` | Stores member information and maintains the list of borrowed books. |

## ⚙️ Prerequisites

Before running the project, install:

- Java Development Kit (JDK)
- Visual Studio Code
- A terminal such as the VS Code integrated terminal


## 🖥️ Application Menu

When the application starts, it displays the following menu:

```text
=== LIBRARY MANAGEMENT SYSTEM ===
1. Add New Book
2. Register New Member
3. Display All Books
4. Display Available Books
5. Search Books
6. Borrow Book
7. Return Book
8. Exit
```

Enter the corresponding number to perform an operation.

## 🧪 Example Usage

The application loads sample books and members automatically.

**Sample books include:**
- *Introduction to Algorithms* — Thomas Cormen
- *Effective Java* — Joshua Bloch
- *Clean Code* — Robert Martin

**Sample members include:**
- Alice Johnson — `M001`
- Sam Lee — `M002`

The application also initializes a borrowed-book example for member `M002`.

To test the borrowing feature:

1. Select option `6`.
2. Enter a registered member ID, such as `M001`.
3. Enter the ISBN of an available book.
4. The system displays a confirmation and the calculated due date.

To return a book, select option `7` and enter the member ID and book ISBN.

## 🧠 Key Concepts Demonstrated

- **Encapsulation:** Book and member attributes are managed through class methods.
- **Object-Oriented Design:** The project separates book, member, library, and interface responsibilities.
- **Collections:** `ArrayList` stores books, members, and borrowed-book records.
- **Linear Search:** The application searches records by ISBN, member ID, and keyword.
- **Date Handling:** `LocalDate` calculates and tracks book due dates.
- **Input Validation:** Required text fields are checked to prevent empty values.
- **Error Handling:** Invalid operations produce appropriate messages instead of silently succeeding.

## ⚠️ Limitations

- Records are stored in memory and are lost when the application closes.
- The application uses a console-based interface rather than a graphical user interface.
- The current implementation does not include database integration, authentication, or fine calculation for overdue books.


## 🎯 Learning Outcomes

This project provides practical experience with Java programming, object-oriented design, collection handling, input validation, and date management. It demonstrates how core Java concepts can be combined to develop a functional library management application.

## 👨‍💻 Author

**Lakshya Nunia**

---

⭐ If you find this project useful, consider giving the repository a star!