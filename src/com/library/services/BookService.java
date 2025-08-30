package com.library.services;

import com.library.models.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private List<Book> books = new ArrayList<>();

    // Add a new book
    public void addBook(Book book) {
        books.add(book);
        System.out.println("✅ Book added: " + book);
    }

    // Remove book by ID
    public boolean removeBook(String bookId) {
        return books.removeIf(b -> b.getId().equals(bookId));
    }

    // Search books by title or author (case-insensitive)
    public List<Book> searchBook(String keyword) {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase())
                        || b.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // List all books
    public List<Book> listBooks() {
        return new ArrayList<>(books);
    }

    // Get book by ID (helper method)
    public Book getBookById(String bookId) {
        return books.stream()
                .filter(b -> b.getId().equals(bookId))
                .findFirst()
                .orElse(null);
    }
}
