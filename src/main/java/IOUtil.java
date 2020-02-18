import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IOUtil {
    public static <T> List<List<T>> getLines(String file, String lineSplitter, Function<String, T> parseFunction) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(IOUtil.class.getResourceAsStream(file)));
            String line;
            List<List<T>> ret = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                ret.add(Arrays.stream(line.split(lineSplitter)).map(parseFunction).collect(Collectors.toList()));
            }
            br.close();
            return ret;
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
