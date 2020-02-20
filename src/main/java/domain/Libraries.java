package domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Libraries implements Iterable<Library> {

    public List<Library> entries;

    public Libraries() {
        this.entries = new ArrayList<>();
    }

    public void add(Library library) {
        entries.add(library);
    }

    public List<Library> getEntries() {
        entries.sort(Comparator.comparingDouble(Library::getScorePerDay));
        return entries;
    }

    @Override
    public Iterator<Library> iterator() {
        return entries.iterator();
    }

    public int size() {
        return entries.size();
    }

    public Library getFirst(){
        return entries.get(0);
    }

    public Library get(int i){
        return getEntries().get(i);
    }
}
