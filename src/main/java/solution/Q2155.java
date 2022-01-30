package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "All Divisions With the Highest Score of a Binary Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/all-divisions-with-the-highest-score-of-a-binary-array/"
)
public class Q2155 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<Integer> maxScoreIndices(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        int[] psum = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            psum[i] = psum[i - 1] + nums[i - 1];
        }

        int max = 0;
        // 0 to i - 1  i to n - 1
        for(int i = 0; i <= n; i++) {
            int tot = psum[n];
            int l = i == 0 ? 0 : psum[i] - psum[0];
            int r = i == n ? 0 : psum[n] - psum[i];

            max = Math.max(max, i - l + r);
        }

        for(int i = 0; i <= n; i++) {
            int l = i == 0 ? 0 : psum[i] - psum[0];
            int r = i == n ? 0 : psum[n] - psum[i];

            if(i - l + r == max) {
                ans.add(i);
            }
        }

        return ans;
    }
}
