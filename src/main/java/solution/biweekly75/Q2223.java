package solution.biweekly75;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum of Scores of Built Strings",
        difficulty = QDifficulty.HARD,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/biweekly-contest-75/problems/sum-of-scores-of-built-strings/"
)
public class Q2223 {
    /**
     * Z algorithm
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public long sumScores(String s) {
        char[] arr = s.toCharArray();
        int n = s.length();
        int[] z = new int[n];
        long ans = n;

        int x = 0, y = 0;
        for(int i = 1; i < n; i++) {
            z[i] = Math.max(0, Math.min(z[i - x], y - i + 1));
            while(i + z[i] < n && arr[z[i]] == arr[i + z[i]]) {
                x = i;
                y = i + z[i];
                z[i]++;
            }
        }

        for(int i = 1; i < n; i++) {
            ans += z[i];
        }

        return ans;
    }
}
