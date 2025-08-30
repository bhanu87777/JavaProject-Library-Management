package com.library.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String userId;
    private String bookId;
    private LocalDate issueDate;
    private LocalDate returnDate;

    // Constructor
    public Transaction(String id, String userId, String bookId, LocalDate issueDate) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.returnDate = null; // not yet returned
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    @Override
    public String toString() {
        return "[Transaction ID=" + id +
                ", User=" + userId +
                ", Book=" + bookId +
                ", Issued=" + issueDate +
                ", Returned=" + (returnDate != null ? returnDate : "Not yet returned") + "]";
    }
}
