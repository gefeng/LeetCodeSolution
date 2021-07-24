package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Number of Visible People in a Queue",
        difficulty = QDifficulty.HARD,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/number-of-visible-people-in-a-queue/"
)
public class Q1944 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = n - 1; i >= 0; i--) {
            int h = heights[i];

            int cnt = 0;
            while(!stack.isEmpty() && stack.peek() < h) {
                stack.pop();
                cnt++;
            }

            if(!stack.isEmpty()) {
                cnt++;
            }

            stack.push(h);
            res[i] = cnt;
        }

        return res;
    }
}
