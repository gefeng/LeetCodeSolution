package solution.weekly291;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Total Appeal of A String",
        difficulty = QDifficulty.HARD,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/weekly-contest-291/problems/total-appeal-of-a-string/"
)
public class Q2262 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public long appealSum(String s) {
        int n = s.length();
        long ans = 0;

        int[] pre = new int[26];
        Arrays.fill(pre, -1);

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int j = pre[c - 'a'];

            ans += (i - pre[c - 'a']) * (n - i);

            pre[c - 'a'] = i;
        }

        return ans;
    }
}
