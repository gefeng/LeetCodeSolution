package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Swaps To Make Sequences Increasing",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/"
)
public class Q801 {
    /**
     * state:
     *  (i, keep) denotes make a[0..i] strictly increasing by keeping last one
     *  (i, swap) denotes make a[0..i] strictly increasing by swapping last one
     * transition:
     *  if a[i + 1] > a[i] and b[i + 1] > b[i]
     *      (i + 1, keep) = min(i, keep)
     *      (i + 1, swap) = min(i, swap) + 1
     *  if a[i + 1] > b[i] and b[i + 1] > a[i]
     *      (i + 1, keep) = min(i, swap)
     *      (i + 1, swap) = min(i, keep) + 1
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] dp = new int[n][2];

        int keep = 0;
        int swap = 1;

        for(int i = 1; i < n; i++) {
            int nkeep = n + 1;
            int nswap = n + 1;
            if(nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                nkeep = keep;
                nswap = swap + 1;
            }
            if(nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                nkeep = Math.min(nkeep, swap);
                nswap = Math.min(nswap, keep + 1);
            }

            keep = nkeep;
            swap = nswap;
        }

        return Math.min(keep, swap);
    }
}
