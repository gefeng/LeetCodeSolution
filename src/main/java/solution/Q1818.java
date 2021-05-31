package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Absolute Sum Difference",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/minimum-absolute-sum-difference/"
)
public class Q1818 {
    private static final int MODULO = (int)1e9 + 7;
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;

        int[] sorted = Arrays.copyOf(nums1, n);
        Arrays.sort(sorted);

        long sum = 0;
        int maxDiff = 0;
        for(int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            int idx = Arrays.binarySearch(sorted, nums2[i]);
            idx = idx < 0 ? ~idx : idx;

            sum += diff;

            if(idx != n) {
                maxDiff = sorted[idx] - nums2[i] < diff ? Math.max(maxDiff, diff - sorted[idx] + nums2[i]) : maxDiff;
            }
            if(idx != 0) {
                maxDiff = nums2[i] - sorted[idx - 1] < diff ? Math.max(maxDiff, diff - nums2[i] + sorted[idx - 1]) : maxDiff;
            }
        }

        return (int)((sum - maxDiff) % MODULO);
    }
}
