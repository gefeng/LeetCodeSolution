package solution.weekly299;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Score Of Spliced Array",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/weekly-contest-299/problems/maximum-score-of-spliced-array/"
)
public class Q2321 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        return Math.max(maxSubarray(nums1, nums2), maxSubarray(nums2, nums1));
    }

    private int maxSubarray(int[] s, int[] t) {
        int n = s.length;

        int max = 0;
        int sum = 0;

        for(int i = 0; i < n; i++) {
            int v = t[i] - s[i];
            sum += v;
            sum = Math.max(0, sum);
            max = Math.max(max, sum);
        }

        sum = 0;
        for(int i = 0; i < n; i++) {
            sum += s[i];
        }

        return sum + max;
    }
}
