import domain.ScannedLibraryResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputBuilder {

    public static void buildOutput(String outputFile, List<ScannedLibraryResult> result) {
        List<String> outputLines = new ArrayList<>();

        outputLines.add(getLineOne(result)); //line 0: # libs
        outputLines.addAll(getOtherLines(result));

        IOUtil.writeLines(outputFile, outputLines);
    }

    private static String getLineOne(List<ScannedLibraryResult> scan$ScannedLibraryResults) {
        return String.valueOf(scan$ScannedLibraryResults.size());
    }

    private static List<String> getOtherLines(List<ScannedLibraryResult> scannedLibraryResults) {
        return scannedLibraryResults.stream()
                .flatMap(scannedLibraryresult -> scannedLibraryresult.getResult().stream())
                .collect(Collectors.toList());
    }
}
