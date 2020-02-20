import domain.Book;
import domain.Libraries;
import domain.Library;
import domain.ScannedLibraryResult;

import java.util.*;
import java.util.stream.IntStream;

public class Main {


    private static int totalNumberOfDays;
    private static Map<Book, List<Library>> booksInLibrary = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Reading a");
        run("a_example.txt");

        System.out.println("Reading b");
        run("b_read_on.txt");

        System.out.println("Reading c");
        run("c_incunabula.txt");

        System.out.println("Reading d");
        run("d_tough_choices.txt");

        System.out.println("Reading e");
        run("e_so_many_books.txt");

        System.out.println("Reading f");
        run("f_libraries_of_the_world.txt");

        System.out.println("barts mom");
        System.out.println("is a milf!");
    }

    public static void run(String fileName) {
        List<List<Integer>> lines = IOUtil.getLines("src/input/" + fileName, " ", Integer::parseInt);

        int index = 0;
        totalNumberOfDays = lines.get(0).get(2);
        lines.remove(0);

        HashMap<Integer, Book> books = new HashMap<>();

        List<Integer> booksList = lines.get(0);
        IntStream.range(0, booksList.size()).forEachOrdered(i -> books.put(i, Book.create(i, booksList.get(i))));
        lines.remove(0);

        Libraries libraries = initLibraries(lines, books);
        int currentLibraryCounter = 0;
        Library currentLibrary = libraries.get(currentLibraryCounter);
        Libraries startedLibraries = new Libraries();
        currentLibrary.startSignUp(0);

        for (int currentDay = 0; currentDay < totalNumberOfDays; currentDay++) {
            if (currentLibrary.isFinishedSigningUp(currentDay)) {
                currentLibrary.startScanning();
                startedLibraries.add(currentLibrary);
                currentLibraryCounter++;
                currentLibrary = libraries.get(currentLibraryCounter);
                currentLibrary.startSignUp(currentDay);
            }
            for (Library library : libraries) {
                if (library.isScanning() && !library.isFinishedScanning()) {
                    ArrayList<Book> newlyScannedBooks = library.scanDay();
                    for (Book newlyScannedBook : newlyScannedBooks) {
                        List<Library> libHasBook = booksInLibrary.get(newlyScannedBook);
                        libHasBook.forEach(l -> l.deleteBook(newlyScannedBook));
                    }
                }
            }
        }
        ArrayList<ScannedLibraryResult> result = new ArrayList<>();

        startedLibraries.entries.stream().filter(x-> !x.getBooks().isEmpty()).forEach(x -> result.add(new ScannedLibraryResult(x)));

        OutputBuilder.buildOutput("src/output/" + fileName, result);
    }

    private static Libraries initLibraries(List<List<Integer>> lines, HashMap<Integer, Book> books) {
        Libraries libraries = new Libraries();
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).isEmpty()) {
                break;
            }
            List<Integer> libInfo = lines.get(i);
            i++;
            Set<Integer> bookInfo = new HashSet<>(lines.get(i));
            Library library = new Library(libraries.size(), libInfo.get(1), libInfo.get(2));
            libraries.add(library);
            bookInfo.forEach(x -> {
                Book book = books.get(x);
                library.addBook(books.get(x));
                booksInLibrary.computeIfAbsent(book, b -> new ArrayList<Library>()).add(library);
            });
            library.calcScorePerDay(false, getTotalNumberOfDays());
        }
        return libraries;
    }

    public static int getTotalNumberOfDays() {
        return totalNumberOfDays;
    }

    public static void setTotalNumberOfDays(int totalNumberOfDays) {
        Main.totalNumberOfDays = totalNumberOfDays;
    }
}

