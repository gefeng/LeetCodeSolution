package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Ways to Separate Numbers",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-ways-to-separate-numbers/"
)
public class Q1977 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int numberOfCombinations(String num) {
        if(num.charAt(0) == '0') {
            return 0;
        }

        int n = num.length();

        int[][] lcp = new int[n + 1][n + 1];
        for(int i = n - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                if(num.charAt(i) == num.charAt(j)) {
                    lcp[i][j] = lcp[i + 1][j + 1] + 1;
                }
            }
        }

        return dfs(num, 0, 0, lcp, new Integer[n + 1][n + 1]);
    }

    private int dfs(String num, int cur, int preLen, int[][] lcp, Integer[][] memo) {
        int n = num.length();

        if(cur == n) {
            return 1;
        }

        if(memo[cur][preLen] != null) {
            return memo[cur][preLen];
        }

        int cnt = 0;

        cnt = dfs(num, cur + 1, preLen + 1, lcp, memo);

        if(cur > 0 && cur + preLen <= n && num.charAt(cur) != '0') {
            int offset = 0;
            int pre = cur - preLen;
            int len = lcp[pre][cur];
            if(len >= preLen || num.charAt(pre + len) <= num.charAt(cur + len)) {
                offset = preLen;
            } else {
                offset = preLen + 1;
            }

            if(cur + offset <= n) {
                cnt = (cnt + dfs(num, cur + offset, offset, lcp, memo)) % MOD;
            }

        }

//         if(n - cur < preLen || num.charAt(cur) == '0') {
//             return memo[cur][preLen] = cnt;
//         }

//         int offset = 0;
//         if(preLen != 0) {
//             int pre = cur - preLen;
//             int len = lcp[pre][cur];
//             if(len >= preLen || num.charAt(pre + len) <= num.charAt(cur + len)) {
//                 offset = preLen - 1;
//             } else {
//                 offset = preLen;
//             }
//             //System.out.println(pre + " " + cur + " " + offset + " " + len);
//         }
//         for(int i = cur + offset; i < n; i++) {
//             cnt = (cnt + dfs(num, i + 1, i - cur + 1, lcp, memo)) % MOD;
//         }

        return memo[cur][preLen] = cnt;
    }
}
