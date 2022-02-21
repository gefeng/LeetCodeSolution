package solution.biweekly72;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Equal and Divisible Pairs in an Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/contest/biweekly-contest-72/problems/count-equal-and-divisible-pairs-in-an-array/"
)
public class Q2176 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(1)
     * */
    public int countPairs(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(nums[i] == nums[j] && (i * j % k == 0)) {
                    ans++;
                }
            }
        }

        return ans;
    }
}
