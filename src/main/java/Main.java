import java.util.List;

public class Main {

    public static void main(String[] args) {
        run("src/input/a_example.txt");
        run("src/input/b_read_on.txt");
        run("src/input/c_incunabula.txt");
        run("src/input/d_tough_choices.txt");
        run("src/input/e_so_many_books.txt");
        System.out.println("barts mom");
        System.out.println("is a milf!");
    }

    public static void run(String fileName) {
        List<List<Integer>> lines = IOUtil.getLines(fileName, " ", Integer::parseInt);


        OutputBuilder.buildOutput(fileName + ".out", null);
    }
}
