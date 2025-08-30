package com.library.services;

import com.library.models.Book;
import com.library.models.Transaction;
import com.library.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionService {
    private List<Transaction> transactions = new ArrayList<>();
    private BookService bookService;
    private UserService userService;

    public TransactionService(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    // Issue a book to a user
    public boolean issueBook(String transactionId, String userId, String bookId) {
        User user = userService.getUserById(userId);
        Book book = bookService.getBookById(bookId);

        if (user == null) {
            System.out.println("❌ User not found.");
            return false;
        }
        if (book == null || book.getAvailableCopies() <= 0) {
            System.out.println("❌ Book not available.");
            return false;
        }

        book.decreaseCopies(1);
        Transaction transaction = new Transaction(transactionId, userId, bookId, LocalDate.now());
        transactions.add(transaction);

        System.out.println("✅ Book issued: " + transaction);
        return true;
    }

    // Return a book
    public boolean returnBook(String transactionId) {
        for (Transaction t : transactions) {
            if (t.getId().equals(transactionId) && !t.isReturned()) {
                Book book = bookService.getBookById(t.getBookId());
                if (book != null) {
                    book.increaseCopies(1);
                }
                t.setReturnDate(LocalDate.now());
                System.out.println("✅ Book returned: " + t);
                return true;
            }
        }
        System.out.println("❌ Transaction not found or already returned.");
        return false;
    }

    // View transaction history
    public List<Transaction> viewHistory() {
        return new ArrayList<>(transactions);
    }
}
