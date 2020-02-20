package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Result {
    private List<ScannedLibraryresult> libraryResults = new ArrayList<>();

    public Result() {
    }

    public Result(List<ScannedLibraryresult> getLibraryResults) {
        this.libraryResults = getLibraryResults;
    }

    public List<ScannedLibraryresult> getLibraryResults() {
        return libraryResults;
    }

    public void setLibraryResults(List<ScannedLibraryresult> libraryResults) {
        this.libraryResults = libraryResults;
    }

    //line 0: # libs
    public String getLineOne() {
        return String.valueOf(libraryResults.size());
    }

    //other lines
    public List<String> getOtherLines(){
        return libraryResults.stream()
                .flatMap(scannedLibraryresult -> scannedLibraryresult.getResult().stream())
                .collect(Collectors.toList());
    }
}

class ScannedLibraryresult {
    private Library library;

    public ScannedLibraryresult() {
    }

    public ScannedLibraryresult(Library library) {
        this.library = library;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    //line 1: libraryId #books
    public String getLineOne() {
        return library.getId() + " " + library.getScannedBooks().size();
    }

    //line 2: firstScannedBookId secondScannedBookId ...
    public String getLineTwo() {
        return library.getScannedBooksList().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    public List<String> getResult(){
        return List.of(getLineOne(), getLineTwo());
    }
}
