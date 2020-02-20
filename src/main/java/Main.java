import domain.Book;
import domain.Libraries;
import domain.Library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        run("src/input/a_example.txt");

        System.out.println("Reading b");
        run("src/input/b_read_on.txt");

        System.out.println("Reading c");
        run("src/input/c_incunabula.txt");

        System.out.println("Reading d");
        run("src/input/d_tough_choices.txt");

        System.out.println("Reading e");
        run("src/input/e_so_many_books.txt");

        System.out.println("barts mom");
        System.out.println("is a milf!");
    }

    public static void run(String fileName) {
        List<List<Integer>> lines = IOUtil.getLines(fileName, " ", Integer::parseInt);

        int index = 0;
        Integer DaysOfScanning = lines.get(0).get(2);
        totalNumberOfDays = lines.get(0).get(2);
        lines.remove(0);

        HashMap<Integer, Book> books = new HashMap<>();

        List<Integer> booksList = lines.get(0);
        IntStream.range(0, booksList.size()).forEachOrdered(i -> books.put(i, Book.create(i, booksList.get(i))));
        lines.remove(0);

        Libraries libraries = initLibraries(lines, books);

//        OutputBuilder.buildOutput(fileName + ".out", null);
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
