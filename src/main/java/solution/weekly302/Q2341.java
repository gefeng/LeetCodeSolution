package solution.weekly302;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Number of Pairs in Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/contest/weekly-contest-302/problems/maximum-number-of-pairs-in-array/"
)
public class Q2341 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] numberOfPairs(int[] nums) {
        int[] cnt = new int[101];
        for(int x : nums) {
            cnt[x]++;
        }

        int[] ans = new int[2];
        for(int i = 0; i < 101; i++) {
            ans[0] += cnt[i] / 2;
            ans[1] += cnt[i] % 2;
        }

        return ans;
    }
}
