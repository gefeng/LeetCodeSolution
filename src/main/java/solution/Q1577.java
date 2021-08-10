package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Number of Ways Where Square of Numbers Is Equal to Product of Two Numbers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/number-of-ways-where-square-of-number-is-equal-to-product-of-two-numbers/"
)
public class Q1577 {
    /**
     * Time:  O(M ^ 2 + N ^ 2)
     * Space: O(M + N)
     * */
    public int numTriplets(int[] nums1, int[] nums2) {
        return findTriplets(nums1, nums2) + findTriplets(nums2, nums1);
    }

    private int findTriplets(int[] nums1, int[] nums2) {
        int res = 0;
        Map<Long, Integer> freqMap = new HashMap<>();
        for(int num : nums1) {
            long sqr = (long)num * num;
            freqMap.put(sqr, freqMap.getOrDefault(sqr, 0) + 1);
        }

        for(int i = 0; i < nums2.length - 1; i++) {
            for(int j = i + 1; j < nums2.length; j++) {
                int f = freqMap.getOrDefault((long)nums2[i] * nums2[j], 0);
                res += f;
            }
        }

        return res;
    }
}
