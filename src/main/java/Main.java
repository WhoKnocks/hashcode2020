import domain.Book;
import domain.Libraries;
import domain.Library;
import domain.ScannedLibraryResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Main {
    private static int totalNumberOfDays;

//    _          __________                              _,
//    _.-(_)._     ."          ".      .--""--.          _.-{__}-._
//            .'________'.   | .--------. |    .'        '.      .:-'`____`'-:.
//            [____________] /` |________| `\  /   .'``'.   \    /_.-"`_  _`"-._\
//            /  / .\/. \  \|  / / .\/. \ \  ||  .'/.\/.\'.  |  /`   / .\/. \   `\
//            |  \__/\__/  |\_/  \__/\__/  \_/|  : |_/\_| ;  |  |    \__/\__/    |
//            \            /  \            /   \ '.\    /.' / .-\                /-.
//            /'._  --  _.'\  /'._  --  _.'\   /'. `'--'` .'\/   '._-.__--__.-_.'   \
//            /_   `""""`   _\/_   `""""`   _\ /_  `-./\.-'  _\'.    `""""""""`    .'`\
//            (__/    '|    \ _)_|           |_)_/            \__)|        '       |   |
//            |_____'|_____|   \__________/   |              |;`_________'________`;-'
//    jgs'----------'    '----------'   '--------------'`--------------------`
//



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

        System.out.println("barts mom");
        System.out.println("is a milf!");
    }

    public static void run(String fileName) {
        List<List<Integer>> lines = IOUtil.getLines("src/input/" + fileName, " ", Integer::parseInt);

        int index = 0;
        Integer DaysOfScanning = lines.get(0).get(2);
        totalNumberOfDays = lines.get(0).get(2);
        lines.remove(0);

        HashMap<Integer, Book> books = new HashMap<>();

        List<Integer> booksList = lines.get(0);
        IntStream.range(0, booksList.size()).forEachOrdered(i -> books.put(i, Book.create(i, booksList.get(i))));
        lines.remove(0);

        Libraries libraries = initLibraries(lines, books);

        OutputBuilder.buildOutput("src/output/" + fileName, getExampleResult());
    }

    private static List<ScannedLibraryResult> getExampleResult() {
        Book book0 = new Book(0);
        Book book1 = new Book(1);
        Book book2 = new Book(2);
        Book book3 = new Book(3);
        Book book4 = new Book(4);
        Book book5 = new Book(5);

        Map<Integer, Book> books = new HashMap<>();
        books.put(book0.getId(), book0);
        books.put(book1.getId(), book1);
        books.put(book2.getId(), book2);
        books.put(book3.getId(), book3);
        books.put(book4.getId(), book4);
        books.put(book5.getId(), book5);

        Library lib0 = new Library(0, 1, 1, books);
        Library lib1 = new Library(1, 1, 1, books);

        lib1.scanBook(book5);
        lib1.scanBook(book2);
        lib1.scanBook(book3);
        lib0.scanBook(book0);
        lib0.scanBook(book1);
        lib0.scanBook(book2);
        lib0.scanBook(book3);
        lib0.scanBook(book4);

        return List.of(
                new ScannedLibraryResult(lib1),
                new ScannedLibraryResult(lib0));
    }

    private static Libraries initLibraries(List<List<Integer>> lines, HashMap<Integer, Book> books) {
        Libraries libraries = new Libraries();
        for (int i = 0; i < lines.size();  i++) {
            if(lines.get(i).isEmpty()){
                break;
            }
            List<Integer> libInfo = lines.get(i);
            i++;
            List<Integer> bookInfo = lines.get(i);
            Library library = new Library(libraries.size(), libInfo.get(1), libInfo.get(2));
            libraries.add(library);
            bookInfo.forEach(x -> library.addBook(books.get(x)));
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

