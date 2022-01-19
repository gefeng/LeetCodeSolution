package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Dota2 Senate",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/dota2-senate/"
)
public class Q649 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String predictPartyVictory(String senate) {
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        int n = senate.length();

        for(int i = 0; i < n; i++) {
            if(senate.charAt(i) == 'R') q1.offer(i);
            else q2.offer(i);
        }

        while(true) {
            if(q1.isEmpty()) return "Dire";
            if(q2.isEmpty()) return "Radiant";

            int i = q1.peek();
            int j = q2.peek();

            if(i < j) {
                q2.poll();
                q1.offer(q1.poll() + n);
            } else {
                q1.poll();
                q2.offer(q2.poll() + n);
            }
        }
    }
}
