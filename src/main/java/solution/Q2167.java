package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Time to Remove All Cars Containing Illegal Goods",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-time-to-remove-all-cars-containing-illegal-goods/"
)
public class Q2167 {
    /**
     * state:
     *  prefix[i] denotes minimum cost to remove all 1s in prefix of s with length i
     *  suffix[i] denotes minimum cost to remove all 1s in suffix of s with length i
     * transition:
     *  prefix[i] = prefix[i - 1] if s[i - 1] == '0'
     *  prefix[i] = min(prefix[i - 1] + 2, i) if s[i - 1] == '1'
     *  the same for suffix
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public int minimumTime(String s) {
        int n = s.length();
        int ans = Integer.MAX_VALUE;
        int[] prefix = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            if(s.charAt(i - 1) == '0') {
                prefix[i] = prefix[i - 1];
            } else {
                prefix[i] = Math.min(prefix[i - 1] + 2, i);
            }
        }

        int min = 0;
        for(int i = 1; i <= n; i++) {
            if(s.charAt(n - i) == '1') {
                min = Math.min(min + 2, i);
            }
            ans = Math.min(ans, prefix[n - i] + min);
        }

        return ans;
    }
}
