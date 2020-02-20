package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScannedLibraryResult {
    private Library library;

    public ScannedLibraryResult() {
    }

    public ScannedLibraryResult(Library library) {
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
        return library.getId() + " " + library.getScannedBooksList().size();
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
