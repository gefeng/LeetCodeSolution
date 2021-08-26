package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Optimal Division",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/optimal-division/"
)
public class Q553 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String optimalDivision(int[] nums) {
        int n = nums.length;
        StringBuilder sb = new StringBuilder();
        if(n == 1) {
            return Integer.toString(nums[0]);
        }
        if(n == 2) {
            sb.append(nums[0]).append("/").append(nums[1]);
            return sb.toString();
        }

        sb.append(nums[0]).append("/").append("(");
        for(int i = 1; i < n - 1; i++) {
            sb.append(nums[i]).append("/");
        }
        sb.append(nums[n - 1]).append(")");

        return sb.toString();
    }
}
