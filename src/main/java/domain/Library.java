package domain;

import java.util.HashMap;
import java.util.Map;

public class Library {

    private int id;
    private int timeToSignUp;
    private int booksPerDay;
    private Map<Integer, Book> books = new HashMap<>();

    private Map<Integer, Book> scannedBooks = new HashMap<>();

    public Library(int id, int timeToSignUp, int booksPerDay) {
        this.id = id;
        this.timeToSignUp = timeToSignUp;
        this.booksPerDay = booksPerDay;
    }

    public Library(int id, int timeToSignUp, int booksPerDay, Map<Integer, Book> books) {
        this.id = id;
        this.timeToSignUp = timeToSignUp;
        this.booksPerDay = booksPerDay;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeToSignUp() {
        return timeToSignUp;
    }

    public void setTimeToSignUp(int timeToSignUp) {
        this.timeToSignUp = timeToSignUp;
    }

    public int getBooksPerDay() {
        return booksPerDay;
    }

    public void setBooksPerDay(int booksPerDay) {
        this.booksPerDay = booksPerDay;
    }

    public Map<Integer, Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public Map<Integer, Book> getScannedBooks() {
        return scannedBooks;
    }

    public void scanBook(Book book){
        scannedBooks.put(book.getId(), book);
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                '}';
    }
}
