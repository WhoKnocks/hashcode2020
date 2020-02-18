import java.util.List;

public class OutputBuilder {

    public static void buildOutput(String outputFile, Object object) {
        List<String> outputLines = List.of();
        IOUtil.writeLines(outputFile, outputLines);
    }

}
