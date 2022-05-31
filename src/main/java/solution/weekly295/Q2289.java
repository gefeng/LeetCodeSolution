package solution.weekly295;

import java.util.*;
import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Steps to Make Array Non-decreasing",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MONOTONIC_STACK,
        url = "https://leetcode.com/contest/weekly-contest-295/problems/steps-to-make-array-non-decreasing/"
)
public class Q2289 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int totalSteps(int[] nums) {
        int n = nums.length;
        int ans = 0;
        Deque<int[]> stack = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            int round = 0;
            while(!stack.isEmpty() && nums[i] >= nums[stack.peek()[0]]) {
                round = Math.max(round, stack.pop()[1]);
            }

            round = stack.isEmpty() ? 0 : round + 1;
            stack.push(new int[] {i, round});
            ans = Math.max(ans, round);
        }

        return ans;
    }
}
