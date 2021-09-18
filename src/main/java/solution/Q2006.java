package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Number of Pairs With Absolute Difference K",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/"
)
public class Q2006 {
    /**
     * Time:  O(N)
     * Space: O(max(nums))
     * */
    public int countKDifference(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;

        int[] cnt = new int[101];
        for(int i = 0; i < n; i++) {
            cnt[nums[i]]++;
        }

        for(int i = 0; i < 101; i++) {
            if(cnt[i] > 0 && i + k < 101) {
                ans += cnt[i] * cnt[i + k];
            }
        }

        return ans;
    }
}
