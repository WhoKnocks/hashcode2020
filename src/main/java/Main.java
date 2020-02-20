import domain.Book;
import domain.Libraries;
import domain.Library;

import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

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
        run("src/input/a_example.txt");
//        run("src/input/b_read_on.txt");
//        run("src/input/c_incunabula.txt");
//        run("src/input/d_tough_choices.txt");
//        run("src/input/e_so_many_books.txt");
//        System.out.println("barts mom");
//        System.out.println("is a milf!");
    }

    public static void run(String fileName) {
        List<List<Integer>> lines = IOUtil.getLines(fileName, " ", Integer::parseInt);

        int index = 0;
        Integer DaysOfScanning = lines.get(0).get(2);
        lines.remove(0);

        HashMap<Integer, Book> books = new HashMap<>();

        List<Integer> booksList = lines.get(0);
        IntStream.range(0, booksList.size()).forEachOrdered(i -> books.put(i, Book.create(i, booksList.get(i))));

        Libraries libraries = initLibraries(lines, books);

        OutputBuilder.buildOutput(fileName + ".out", null);
    }


    private static Libraries initLibraries(List<List<Integer>> lines, HashMap<Integer, Book> books) {
        Libraries libraries = new Libraries();
        for (int i = 1; i < lines.size(); i++) {
            List<Integer> libInfo = lines.get(i);
            i++;
            List<Integer> bookInfo = lines.get(i);
            Library library = new Library(libraries.size(), libInfo.get(1), libInfo.get(2));
            libraries.add(library);
            bookInfo.forEach(x -> library.addBook(books.get(x)));
            library.calcScorePerDay();
        }
        return libraries;
    }



}
