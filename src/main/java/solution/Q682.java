package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Problem(
        title = "Baseball Game",
        difficulty = QDifficulty.EASY,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/baseball-game/"
)
public class Q682 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int calPoints(String[] ops) {
        List<Integer> l = new ArrayList<>();
        for(String s : ops) {
            if(s.equals("+")) {
                l.add(l.get(l.size() - 1) + l.get(l.size() - 2));
            } else if(s.equals("D")) {
                l.add(l.get(l.size() - 1) * 2);
            } else if(s.equals("C")) {
                l.remove(l.size() - 1);
            } else {
                l.add(Integer.parseInt(s));
            }
        }

        int sum = 0;
        for(int x : l) sum += x;

        return sum;
    }
}
