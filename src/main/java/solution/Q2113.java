package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Elements in Array After Removing and Replacing Elements",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/elements-in-array-after-removing-and-replacing-elements/"
)
public class Q2113 {
    /**
     * Time:  O(M)
     * Space: O(M)
     * */
    public int[] elementInNums(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        int[] ans = new int[m];

        for(int i = 0; i < m; i++) {
            int[] q = queries[i];
            int t = q[0] % (2 * n);
            int idx = q[1];

            int len = 0;
            if(t > n) {
                len = t % n;
            } else if(t < n) {
                len = n - t % n;
            }

            if(idx >= len) {
                ans[i] = -1;
            } else {
                if(t > n) {
                    ans[i] = nums[idx];
                } else {
                    ans[i] = nums[idx + t];
                }
            }
        }

        return ans;
    }
}
