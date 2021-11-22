package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum of Even Numbers After Queries",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/sum-of-even-numbers-after-queries/"
)
public class Q985 {
    /**
     * Time:  O(M + N)
     * Space: O(M + N)
     * */
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        int sum = 0;
        for(int x : nums) {
            sum += x % 2 == 0 ? x : 0;
        }

        for(int i = 0; i < n; i++) {
            int[] q = queries[i];
            int val = q[0];
            int idx = q[1];

            if((nums[idx] + val) % 2 == 0) {
                sum += nums[idx] + val;
            }
            if(nums[idx] % 2 == 0) {
                sum -= nums[idx];
            }
            nums[idx] += val;
            ans[i] = sum;
        }

        return ans;
    }
}
