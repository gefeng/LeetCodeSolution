package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Design Front Middle Back Queue",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/design-front-middle-back-queue/"
)
public class Q1670 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */

    private Deque<Integer> d1;
    private Deque<Integer> d2;

    public Q1670() {
        d1 = new ArrayDeque<>();
        d2 = new ArrayDeque<>();
    }

    public void pushFront(int val) {
        d1.offerFirst(val);
        balance();
    }

    public void pushMiddle(int val) {
        d1.offerLast(val);
        balance();
    }

    public void pushBack(int val) {
        d2.offerLast(val);
        balance();
    }

    public int popFront() {
        if(d1.isEmpty() && d2.isEmpty()) {
            return -1;
        }
        int val = d1.isEmpty() ? d2.pollFirst() : d1.pollFirst();
        balance();
        return val;
    }

    public int popMiddle() {
        if(d1.isEmpty() && d2.isEmpty()) {
            return -1;
        }
        int val = d1.size() == d2.size() ? d1.pollLast() : d2.pollFirst();
        balance();
        return val;
    }

    public int popBack() {
        if(d2.isEmpty()) {
            return -1;
        }
        int val = d2.pollLast();
        balance();
        return val;
    }

    private void balance() {
        int m = d1.size();
        int n = d2.size();

        if(m > n) {
            d2.offerFirst(d1.pollLast());
        } else if(n - m > 1) {
            d1.offerLast(d2.pollFirst());
        }
    }
}
