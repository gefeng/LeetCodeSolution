package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Flatten Nested List Iterator",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/flatten-nested-list-iterator/"
)
public class Q341 {
    /*
    * it's a tree problem
    * Solution 1. Recursively flatten the nested list and save each integer in list
    * Solution 2. Stack
    * Solution 3. Stack of Iterator
    * */
    public interface NestedInteger {
        boolean isInteger();
        Integer getInteger();
        List<NestedInteger> getList();
    }
    public class NestedIterator implements Iterator<Integer> {
        private Stack<ListIterator<NestedInteger>> stack;
        private Integer peeked;
        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new Stack<>();
            stack.push(nestedList.listIterator());
            peeked = null;
        }

        @Override
        public Integer next() {
            if(!hasNext())
                return null;

            int next = peeked;
            peeked = null;
            return next;
        }

        @Override
        public boolean hasNext() {
            if(peeked != null)
                return true;
            while(!stack.isEmpty()) {
                ListIterator<NestedInteger> iterator = stack.peek();
                if(iterator.hasNext()) {
                    NestedInteger next = iterator.next();
                    if(next.isInteger()) {
                        peeked = next.getInteger();
                        break;
                    }
                    else
                        stack.push(next.getList().listIterator());
                } else {
                    stack.pop();
                }
            }

            return peeked != null;
        }
    }
}
