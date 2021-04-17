package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Iterator;
import java.util.NoSuchElementException;

@Problem(
        title = "Peeking Iterator",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/peeking-iterator/"
)
public class Q284 implements Iterator<Integer> {
    private final Iterator<Integer> iterator;
    private Integer peekedValue;
    public Q284(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        this.peekedValue = null;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if(peekedValue == null) {
            if(!iterator.hasNext())
                throw new NoSuchElementException();
            peekedValue = iterator.next();
        }
        return peekedValue;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if(peekedValue == null) {
            if(!iterator.hasNext()) {
                throw new NoSuchElementException();
            }
            return iterator.next();
        }

        int copy = peekedValue;
        peekedValue = null;
        return copy;
    }

    @Override
    public boolean hasNext() {
        if(peekedValue != null)
            return true;

        return iterator.hasNext();
    }
}
