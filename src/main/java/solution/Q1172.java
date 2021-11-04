package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Dinner Plates Stacks",
        difficulty = QDifficulty.HARD,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/dinner-plate-stacks/"
)
public class Q1172 {
    /**
     * Time:  O(logN) for each operations
     * Space: O(N)
     * */
    List<Deque<Integer>> stacks;
    TreeSet<Integer> lm, rm;
    int capacity;
    public Q1172(int capacity) {
        stacks = new ArrayList<>();
        lm = new TreeSet<>();
        rm = new TreeSet<>();
        this.capacity = capacity;
    }

    public void push(int val) {
        if(lm.isEmpty()) {
            Deque<Integer> s = new ArrayDeque();
            s.push(val);
            stacks.add(s);

            int idx = stacks.size() - 1;
            if(s.size() < capacity) {
                lm.add(idx);
            }
            rm.add(idx);
        } else {
            int idx = lm.first();

            Deque<Integer> s = stacks.get(idx);
            s.push(val);

            if(s.size() == capacity) {
                lm.pollFirst();
            }
            rm.add(idx);
        }
    }

    public int pop() {
        if(rm.isEmpty()) {
            return -1;
        }

        int idx = rm.last();
        Deque<Integer> s = stacks.get(idx);

        int ret = s.pop();

        if(s.isEmpty()) {
            rm.pollLast();
        }
        lm.add(idx);

        return ret;
    }

    public int popAtStack(int index) {
        if(index >= stacks.size()) {
            return -1;
        }

        Deque<Integer> s = stacks.get(index);

        if(s.isEmpty()) {
            return -1;
        }

        int ret = s.pop();

        if(s.isEmpty()) {
            rm.remove(index);
        }
        lm.add(index);

        return ret;
    }
}
