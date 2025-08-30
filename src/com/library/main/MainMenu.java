package com.library.main;

import com.library.services.*;
import com.library.models.*;
import com.library.utils.InputValidator;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MainMenu {
    private final BookService bookService;
    private final UserService userService;
    private final TransactionService transactionService;
    private final AuthService authService;
    private final Scanner scanner = new Scanner(System.in);

    private User loggedInUser = null;

    public MainMenu(BookService bookService, UserService userService,
                    TransactionService transactionService, AuthService authService) {
        this.bookService = bookService;
        this.userService = userService;
        this.transactionService = transactionService;
        this.authService = authService;
    }

    // Start the application loop
    public void start() {
        while (true) {
            if (loggedInUser == null) {
                loginMenu();
            } else {
                if (loggedInUser.isLibrarian()) {
                    librarianMenu();
                } else {
                    studentMenu();
                }
            }
        }
    }

    // Login / Register menu
    private void loginMenu() {
        System.out.println("\n==== LOGIN MENU ====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                registerUser();
                break;
            case "2":
                loginUser();
                break;
            case "0":
                System.exit(0);
            default:
                System.out.println("❌ Invalid choice.");
        }
    }

    private void registerUser() {
        System.out.print("Enter user ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter role (student/librarian): ");
        String role = scanner.nextLine();

        if (!InputValidator.isValidRole(role)) {
            System.out.println("❌ Invalid role.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User(id, name, role, password);
        authService.registerUser(user);
    }

    private void loginUser() {
        System.out.print("Enter username: ");
        String name = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        loggedInUser = authService.login(name, password);
    }

    // Librarian menu
    private void librarianMenu() {
        System.out.println("\n==== LIBRARIAN MENU ====");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. List Books");
        System.out.println("4. View Transactions");
        System.out.println("5. Logout");
        System.out.print("Choose: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                addBook();
                break;
            case "2":
                removeBook();
                break;
            case "3":
                listBooks();
                break;
            case "4":
                viewTransactions();
                break;
            case "5":
                loggedInUser = null;
                break;
            default:
                System.out.println("❌ Invalid choice.");
        }
    }

    // Student menu
    private void studentMenu() {
        System.out.println("\n==== STUDENT MENU ====");
        System.out.println("1. Search Book");
        System.out.println("2. Issue Book");
        System.out.println("3. Return Book");
        System.out.println("4. Logout");
        System.out.print("Choose: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                searchBook();
                break;
            case "2":
                issueBook();
                break;
            case "3":
                returnBook();
                break;
            case "4":
                loggedInUser = null;
                break;
            default:
                System.out.println("❌ Invalid choice.");
        }
    }

    // Librarian actions
    private void addBook() {
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Copies: ");
        int copies = Integer.parseInt(scanner.nextLine());

        Book book = new Book(id, title, author, copies);
        bookService.addBook(book);
    }

    private void removeBook() {
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine();
        if (bookService.removeBook(id)) {
            System.out.println("✅ Book removed.");
        } else {
            System.out.println("❌ Book not found.");
        }
    }

    private void listBooks() {
        List<Book> books = bookService.listBooks();
        if (books.isEmpty()) {
            System.out.println("📭 No books available.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void viewTransactions() {
        List<Transaction> transactions = transactionService.viewHistory();
        if (transactions.isEmpty()) {
            System.out.println("📭 No transactions.");
        } else {
            transactions.forEach(System.out::println);
        }
    }

    // Student actions
    private void searchBook() {
        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();
        List<Book> books = bookService.searchBook(keyword);
        if (books.isEmpty()) {
            System.out.println("📭 No books found.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void issueBook() {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        String transactionId = UUID.randomUUID().toString();
        transactionService.issueBook(transactionId, loggedInUser.getId(), bookId);
    }

    private void returnBook() {
        System.out.print("Enter Transaction ID: ");
        String transactionId = scanner.nextLine();
        transactionService.returnBook(transactionId);
    }
}
