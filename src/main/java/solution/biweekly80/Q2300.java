package solution.biweekly80;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Successful Pairs of Spells and Potions",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/contest/biweekly-contest-80/problems/successful-pairs-of-spells-and-potions/"
)
public class Q2300 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] ans = new int[n];

        Arrays.sort(potions);

        for(int i = 0; i < n; i++) {
            int idx = bs(potions, spells[i], success);

            ans[i] = m - (idx + 1);
        }
        return ans;
    }

    private int bs(int[] nums, long x, long t) {
        int idx = -1;
        int lo = 0;
        int hi = nums.length - 1;

        while(lo <= hi) {
            int mid = lo + hi >> 1;
            if(x * nums[mid] < t) {
                lo = mid + 1;
                idx = mid;
            } else {
                hi = mid - 1;
            }
        }

        return idx;
    }
}
