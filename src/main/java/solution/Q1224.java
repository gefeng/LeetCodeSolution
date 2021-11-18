package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Maximum Equal Frequency",
        difficulty = QDifficulty.HARD,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/maximum-equal-frequency/"
)
public class Q1224 {
    /**
     * Time:  O(N)
     * Space: O(max(nums))
     * */
    public int maxEqualFreq(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int[] f = new int[100001];
        int[] ff = new int[100001];

        int maxf = 0;
        for(int i = 0; i < n; i++) {
            int v = nums[i];

            ff[f[v]]--;
            f[v]++;
            maxf = Math.max(maxf, f[v]);
            ff[f[v]]++;

            // [1 2 2 1 3 3 3]
            if(ff[maxf - 1] * (maxf - 1) == i + 1 - maxf) {
                ans = i + 1;
            }

            // [1 2 2 1 3 3 4]
            if(ff[1] == 1 && maxf * ff[maxf] == i) {
                ans = i + 1;
            }

            // [1 2 3 4 5 6]
            if(maxf == 1) {
                ans = i + 1;
            }
        }

        return ans;
    }
}
