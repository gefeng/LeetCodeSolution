package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Distance Between a Pair of Values",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/maximum-distance-between-a-pair-of-values/"
)
public class Q1855 {
    public int maxDistance(int[] nums1, int[] nums2) {
        int maxDist = 0;
        int i = 0;
        int j = 0;
        while(i < nums1.length && j < nums2.length) {
            if(i > j) {
                j++;
            } else if(nums1[i] <= nums2[j]) {
                maxDist = Math.max(maxDist, j - i);
                j++;
            } else if(nums1[i] > nums2[j]) {
                i++;
            }
        }

        return maxDist;
    }
}
