package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

@Problem(
        title = "Design Phone Directory",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/design-phone-directory/"
)
public class Q379 {
    /**
     * Time:  O(N) for constructor, O(1) for each operation
     * Space: O(N)
     * */
    private Set<Integer> used;
    private Queue<Integer> numbers;
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public Q379(int maxNumbers) {
        used = new HashSet<>();
        numbers = new ArrayDeque<>();

        for(int i = 0; i < maxNumbers; i++) {
            numbers.add(i);
        }
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(numbers.isEmpty()) {
            return -1;
        }
        int num = numbers.poll();
        used.add(num);

        return num;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !used.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if(used.contains(number)) {
            used.remove(number);
            numbers.offer(number);
        }
    }
}
