package com.library.main;

import com.library.services.*;
import com.library.utils.*;
import com.library.models.*;

import java.util.List;

public class LibraryApp {
    public static void main(String[] args) {
        System.out.println(Constants.MSG_WELCOME);

        // Initialize services
        BookService bookService = new BookService();
        UserService userService = new UserService();
        TransactionService transactionService = new TransactionService(bookService, userService);
        AuthService authService = new AuthService(userService);

        // Load persisted data
        List<Book> savedBooks = FileUtils.loadFromFile(Constants.BOOKS_FILE);
        if (savedBooks != null) savedBooks.forEach(bookService::addBook);

        List<User> savedUsers = FileUtils.loadFromFile(Constants.USERS_FILE);
        if (savedUsers != null) savedUsers.forEach(userService::addUser);

        List<Transaction> savedTransactions = FileUtils.loadFromFile(Constants.TRANSACTIONS_FILE);
        if (savedTransactions != null) savedTransactions.forEach(transactionService.viewHistory()::add);

        // Run main menu
        MainMenu menu = new MainMenu(bookService, userService, transactionService, authService);
        menu.start();

        // Save data before exit
        FileUtils.saveToFile(bookService.listBooks(), Constants.BOOKS_FILE);
        FileUtils.saveToFile(userService.listUsers(), Constants.USERS_FILE);
        FileUtils.saveToFile(transactionService.viewHistory(), Constants.TRANSACTIONS_FILE);

        System.out.println(Constants.MSG_EXIT);
    }
}