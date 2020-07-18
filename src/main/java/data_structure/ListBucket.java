package data_structure;

import java.util.LinkedList;
import java.util.List;

public class ListBucket implements Bucket {
    private List<Integer> container;

    public ListBucket() {
        container = new LinkedList<Integer>();
    }

    public void add(int value) {
        if(!contains(value))
            container.add(value);
    }

    public void remove(int value) {
        int index = container.indexOf(value);
        if(index != -1)
            container.remove(index);
    }

    public boolean contains(int value) {
        return container.contains(value);
    }
}
