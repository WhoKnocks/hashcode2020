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

    public void setRLibraryResults(List<ScannedLibraryresult> libraryResults) {
        this.libraryResults = libraryResults;
    }

    //line 0: # libs
    public String getLineOne() {
        return String.valueOf(libraryResults.size());
    }

    //other lines
    public void getOtherLines(){
        //over libraryResults loopen en getLineOne en getLineTwo
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

    //line 1: id #books
    public String getLineOne() {
        return library.getId() + " " + library.getScannedBooks().size();
    }

    //line 2
    public String getLineTwo() {
        return library.getScannedBooksList().stream().map(String::valueOf).collect(Collectors.joining(" "));
    }
}
