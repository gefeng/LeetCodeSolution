package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Set Mismatch",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/set-mismatch/"
)
public class Q645 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] cnt = new int[n + 1];
        for(int x : nums) {
            cnt[x]++;
        }

        int[] ans = new int[2];
        for(int i = 1; i <= n; i++) {
            if(cnt[i] == 2) ans[0] = i;
            if(cnt[i] == 0) ans[1] = i;
        }

        return ans;
    }
}
