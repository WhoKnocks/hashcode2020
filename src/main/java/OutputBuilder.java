import domain.Result;

import java.util.ArrayList;
import java.util.List;

public class OutputBuilder {

    public static void buildOutput(String outputFile, Result result) {
        List<String> outputLines = new ArrayList<>();

        outputLines.add(result.getLineOne());
        outputLines.addAll(result.getOtherLines());

        IOUtil.writeLines(outputFile, outputLines);
    }
}
