package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Number of Recent Calls",
        difficulty = QDifficulty.EASY,
        tag = QTag.QUEUE,
        url = "https://leetcode.com/problems/number-of-recent-calls/"
)
public class Q933 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    Queue<Integer> q;
    public Q933() {
        q = new ArrayDeque<>();
    }

    public int ping(int t) {
        q.offer(t);
        while(q.peek() < t - 3000) {
            q.poll();
        }

        return q.size();
    }
}
