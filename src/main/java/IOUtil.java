import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IOUtil {
    public static <T> List<List<T>> getLines(String file, String lineSplitter, Function<String, T> parseFunction) {
        try {
            List<String> lines = Files.readAllLines(new File(file).toPath());

            return lines.stream().map(line -> Arrays.stream(line.split(lineSplitter))
                    .filter(x -> !x.equalsIgnoreCase(""))
                    .map(parseFunction)
                    .collect(Collectors.toList()))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            throw new RuntimeException("Cannot read file " + file, ex);
        }
    }

    public static void writeLines(String file, List<String> lines) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
            lines.forEach(out::println);
            out.close();
        } catch (Exception e) {
            throw new RuntimeException("Can't save file " + file, e);
        }
    }
}
