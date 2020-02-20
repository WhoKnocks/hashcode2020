package domain;

public class Book {

    private int id;
    private int score;

    public Book(int id) {
        this.id = id;
    }

    public Book(int id, int score) {
        this.id = id;
        this.score = score;
    }

    public static Book create(int i, Integer score) {
        return new Book(i, score);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", score=" + score +
                '}';
    }
}
