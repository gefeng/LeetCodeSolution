package solution.weekly298;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Binary Subsequence Less Than or Equal to K",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/weekly-contest-298/problems/longest-binary-subsequence-less-than-or-equal-to-k/"
)
public class Q2311 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int longestSubsequence(String s, int k) {
        int n = s.length();
        int[] dp = new int[n];
        int ans = 0;
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            dp[i] = cnt;
            cnt += s.charAt(i) == '0' ? 1 : 0;
        }

        ans = cnt;
        for(int i = 0; i < n; i++) {
            int x = 0;
            int j = i;
            while(j >= 0) {
                if(s.charAt(j) == '1') {
                    int d = i - j;
                    if(d < 30 && (x | (1 << d)) <= k) {
                        ans = Math.max(ans, i - j + 1 + dp[j]);
                        x |= (1 << d);
                    } else {
                        break;
                    }
                }
                j--;
            }
        }

        return ans;
    }
}
