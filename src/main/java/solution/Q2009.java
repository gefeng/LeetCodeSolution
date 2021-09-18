package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Minimum Number of Operations to Make Array Continuous",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/"
)
public class Q2009 {
    /**
     * Test each unique number to find possible right bound by using binary search.
     * Count unique presented elements within the window and calculate the cost.
     *
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ans = n;
        Set<Integer> set = new HashSet<>();

        for(int num : nums) {
            set.add(num);
        }

        int[] unique = new int[set.size()];
        int idx = 0;
        for(int num : set) {
            unique[idx++] = num;
        }

        Arrays.sort(unique);

        for(int i = 0; i < unique.length; i++) {
            int l = unique[i];
            int r = l + n - 1;

            int ceiling = Arrays.binarySearch(unique, r);

            if(ceiling >= 0) {
                ceiling++;
            } else {
                ceiling = -ceiling - 1;
            }

            ans = Math.min(ans, n - (ceiling - i));
        }

        return ans;
    }
}
