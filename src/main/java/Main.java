import java.util.List;

public class Main {

    public static void main(String[] args) {
        run("a_example");
        System.out.println("a done");
    }

    public static void run(String fileName) {
        List<List<Integer>> lines = IOUtil.getLines(fileName + ".in", " ", Integer::parseInt);


        OutputBuilder.buildOutput(fileName + ".out", null);
    }
}
