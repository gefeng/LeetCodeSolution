package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Max Value of Equation",
        difficulty = QDifficulty.HARD,
        tag = QTag.QUEUE,
        url = "https://leetcode.com/problems/max-value-of-equation/"
)
public class Q1499 {
    /**
     * Keep track of max value in a k size window by using monotonic deque.
     * Since the equation is given as yi + yj + |xi - xj|, we can transform it to
     * (yj - xj) + (yi + xi). Obviously we need to maximize (yj - xj). The rest of
     * the logic is just the same as finding maximum in sliding window in O(N) time.
     *
     * Time:  O(N)
     * Space: O(K)
     * */
    public int findMaxValueOfEquation(int[][] points, int k) {
        int n = points.length;
        int res = Integer.MIN_VALUE;
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            int[] p = points[i];

            while(!deque.isEmpty() && p[0] - points[deque.peekFirst()][0] > k) {
                deque.pollFirst();
            }

            if(!deque.isEmpty()) {
                int idx = deque.peekFirst();
                res = Math.max(res, p[1] + points[idx][1] + p[0] - points[idx][0]);
            }

            while(!deque.isEmpty() && p[1] - p[0] > points[deque.peekLast()][1] - points[deque.peekLast()][0]) {
                deque.pollLast();
            }


            deque.offer(i);
        }

        return res;
    }
}
