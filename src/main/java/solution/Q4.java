package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Median of Two Sorted Arrays",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url  = "https://leetcode.com/problems/median-of-two-sorted-arrays/"
)
public class Q4 {
    // time: O(m + n) space: O(1)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int i = 0;
        int j = 0;
        int m1 = 0;
        int m2 = 0;

        for(int k = 0; k < len / 2 + 1; k++) {
            m1 = m2;
            if(i < nums1.length && j < nums2.length) {
                if(nums1[i] < nums2[j])
                    m2 = nums1[i++];
                else
                    m2 = nums2[j++];
            }
            else
                m2 = i == nums1.length ? nums2[j++] : nums1[i++];
        }


        if(len % 2 == 0)
            return ((double)m1 + m2) / 2;
        else
            return m2;
    }

    public double findMedianSortedArraysBinarySearch(int[] nums1, int[] nums2) {
        int lenA = nums1.length;
        int lenB = nums2.length;
        if(lenA > lenB)
            return findMedianSortedArraysBinarySearch(nums2, nums1);

        int left = 0;
        int right = lenA;
        int cutPoint1 = 0;
        int cutPoint2 = 0;
        int maxLeft = 0;
        int minRight = 0;
        while(left <= right) {
            cutPoint1 = left + (right - left) / 2;
            cutPoint2 = (lenA + lenB + 1) / 2 - cutPoint1;
            if(cutPoint1 < right && nums1[cutPoint1] < nums2[cutPoint2 - 1]) {
                left = cutPoint1 + 1;
            }
            else if(cutPoint1 > left && nums2[cutPoint2] < nums1[cutPoint1 - 1]) {
                right = cutPoint1 - 1;
            }
            else {
                if(cutPoint1 == 0)
                    maxLeft = nums2[cutPoint2 - 1];
                else if(cutPoint2 == 0)
                    maxLeft = nums1[cutPoint1 - 1];
                else
                    maxLeft = Math.max(nums1[cutPoint1 - 1], nums2[cutPoint2 - 1]);
                if((lenA + lenB) % 2 != 0)
                    return maxLeft;

                if(cutPoint1 == lenA)
                    minRight = nums2[cutPoint2];
                else if(cutPoint2 == lenB)
                    minRight = nums1[cutPoint1];
                else
                    minRight = Math.min(nums1[cutPoint1], nums2[cutPoint2]);
                return ((double)maxLeft + minRight) / 2;
            }
        }
        return 0.0;
    }
}
