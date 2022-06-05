package solution.weekly296;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Design a Text Editor",
        difficulty = QDifficulty.HARD,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/weekly-contest-296/problems/design-a-text-editor/"
)
public class Q2296 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    Deque<Character> deq1;
    Deque<Character> deq2;

    public Q2296() {
        deq1 = new ArrayDeque<>();
        deq2 = new ArrayDeque<>();
    }

    public void addText(String text) {
        int n = text.length();
        for(int i = 0; i < n; i++) {
            deq1.offerLast(text.charAt(i));
        }
    }

    public int deleteText(int k) {
        int n = Math.min(deq1.size(), k);
        for(int i = 0; i < n; i++) {
            deq1.pollLast();
        }

        return n;
    }

    public String cursorLeft(int k) {
        int n = Math.min(deq1.size(), k);
        for(int i = 0; i < n; i++) {
            deq2.offerFirst(deq1.pollLast());
        }

        return left();
    }

    public String cursorRight(int k) {
        int n = Math.min(deq2.size(), k);
        for(int i = 0; i < n; i++) {
            deq1.offerLast(deq2.pollFirst());
        }

        return left();
    }

    private String left() {
        int n = Math.min(deq1.size(), 10);
        char[] s = new char[n];
        for(int i = n - 1; i >= 0; i--) {
            s[i] = deq1.pollLast();
        }

        for(int i = 0; i < n; i++) {
            deq1.offerLast(s[i]);
        }

        return new String(s);
    }
}
