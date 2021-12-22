package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Count Unique Characters of All Substrings of a Given String",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/"
)
public class Q828 {
    /**
     * Calculate "Contribution" of each letter.
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public int uniqueLetterString(String s) {
        int ans = 0;
        int n = s.length();
        int[] l = new int[n];
        int[] r = new int[n];

        int[] pre = new int[26];
        Arrays.fill(pre, -1);
        for(int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'A';
            l[i] = pre[c];
            pre[c] = i;
        }

        Arrays.fill(pre, n);
        for(int i = n - 1; i >= 0; i--) {
            int c = s.charAt(i) - 'A';
            r[i] = pre[c];
            pre[c] = i;
        }

        for(int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'A';
            ans += (i - l[i]) * (r[i] - i);
        }

        return ans;
    }
}
