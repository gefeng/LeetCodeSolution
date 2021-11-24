package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Delete Columns to Make Sorted III",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/delete-columns-to-make-sorted-iii/"
)
public class Q960 {
    /*
       dp[i] denotes min deletions to sort s[0, i] with last char s[i]
       dp[i] = min(dp[j] + i - j - 1) where every s in strs s[i] >= s[j]
   */
    /**
     * state:
     *  dp[i] denotes min deletions to sort s[0, i] with last char s[i]
     * transition:
     *  dp[i] = min(dp[j] + i - j - 1) where every s in strs s[i] >= s[j]
     *
     * Need to scan dp array again to find optimal answer.
     *
     * Time:  O(N * L ^ 2)
     * Space: O(L)
     * */
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int len = strs[0].length();
        int ans = len;
        int[] dp = new int[len];

        for(int i = 0; i < len; i++) {
            dp[i] = i;
        }

        for(int i = 0; i < len; i++) {
            for(int j = 0; j < i; j++) {
                if(isValidCol(strs, j, i)) {
                    dp[i] = Math.min(dp[i], dp[j] + i - j - 1);
                }
            }
        }

        for(int i = 0; i < len; i++) {
            ans = Math.min(ans, dp[i] + len - i - 1);
        }

        return ans;
    }

    private boolean isValidCol(String[] strs, int pc, int c) {
        int n = strs.length;
        for(int i = 0; i < n; i++) {
            if(strs[i].charAt(pc) > strs[i].charAt(c)) {
                return false;
            }
        }

        return true;
    }
}
