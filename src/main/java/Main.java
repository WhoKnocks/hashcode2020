import java.util.List;

public class Main {

    public static void main(String[] args) {
        run("src/input/a_example.txt");
        System.out.println("bens mom");
        System.out.println("is a milf!");
    }

    public static void run(String fileName) {
        List<List<Integer>> lines = IOUtil.getLines(fileName, " ", Integer::parseInt);


        OutputBuilder.buildOutput(fileName + ".out", null);
    }
}
