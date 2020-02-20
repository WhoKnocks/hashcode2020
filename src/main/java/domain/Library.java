package domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library implements Comparable<Library> {

    private int id;
    private int timeToSignUp;
    private int booksPerDay;
    private double scorePerDay;
    private int startDay;
    private List<Book> books = new ArrayList<>();

    private List<Integer> scannedBooksList = new ArrayList<>();
    private boolean isStartedScanning = false;
    private boolean isFinished = false;

    public Library(int id, Integer timeToSignUp, int booksPerDay) {
        this.id = id;
        this.timeToSignUp = timeToSignUp;
        this.booksPerDay = booksPerDay;
    }

    public Library(int id, int timeToSignUp, int booksPerDay, List<Book> books) {
        this.id = id;
        this.timeToSignUp = timeToSignUp;
        this.booksPerDay = booksPerDay;
        this.books = books;
    }

    private void removeBook(Book book) {
        books.remove(book);
    }

    public double calcScorePerDay(boolean takeSignUpTimeIntoAccount, int totalDays) {
        int totScore = 0;
        for (Book book : books) {
            totScore += book.getScore();
        }
        totScore = totScore / books.size() * booksPerDay;
        if (takeSignUpTimeIntoAccount) {
            totScore *= totalDays / (totalDays * timeToSignUp);
        }
        return totScore;
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

    public List<Book> getBooks() {
        return books;
    }

    public List<Integer> getScannedBooksList() {
        return scannedBooksList;
    }

    public void addBook(Book book) {
        books.add(book);
    }


    public void scanBook(Book book) {
        scannedBooksList.add(book.getId());
        book.setScanned(true);
        books.remove(book);
    }

    public ArrayList<Book> scanDay() {
        ArrayList<Book> scannedBooks = new ArrayList<Book>();
        for (int i = 0; i < booksPerDay; i++) {
            if (getBooks().isEmpty()) break;
            books.sort(Comparator.comparingInt(Book::getScore));
            Book book = books.get(0);
            scanBook(book);
            scannedBooks.add(book);
        }
        if (getBooks().isEmpty()) setIsFinished();
        return scannedBooks;
    }

    private void setIsFinished() {
        setFinished(true);
    }

    public double getScorePerDay() {
        return scorePerDay;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public void startSignUp(int currentDay) {
        setStartDay(currentDay);
    }

    public void setScorePerDay(double scorePerDay) {
        this.scorePerDay = scorePerDay;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                '}';
    }

    @Override
    public int compareTo(Library o) {
        return Double.compare(scorePerDay, o.scorePerDay);
    }

    @Override
    public boolean equals(Object obj) {
        Library other = (Library) obj;
        return id == other.id;
    }

    public boolean isFinishedScanning() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isFinishedSigningUp(int currentDay) {
        return currentDay - getStartDay() > getTimeToSignUp();
    }

    public void startScanning() {
        isStartedScanning = true;
    }

    public boolean isScanning() {
        return isStartedScanning;
    }

    public void deleteBook(Book book) {
        getBooks().remove(book);

    }
}
