package com.library.models;

import java.io.Serial;
import java.io.Serializable;

public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String author;
    private int availableCopies;

    // Constructor
    public Book(String id, String title, String author, int availableCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    // Utility methods
    public void increaseCopies(int count) {
        this.availableCopies += count;
    }

    public void decreaseCopies(int count) {
        if (this.availableCopies >= count) {
            this.availableCopies -= count;
        }
    }

    @Override
    public String toString() {
        return "[Book ID=" + id + ", Title=" + title + ", Author=" + author +
                ", Available Copies=" + availableCopies + "]";
    }
}
