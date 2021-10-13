package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Distance to Type a Word Using Two Fingers",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-distance-to-type-a-word-using-two-fingers/"
)
public class Q1320 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int minimumDistance(String word) {
        int w = 6;
        int h = 5;
        int n = word.length();

        return dfs(word, 0, 26, 26, new Integer[n][27][27]);
    }

    private int dfs(String s, int cur, int pre1, int pre2, Integer[][][] memo) {
        int n = s.length();
        if(cur == s.length()) {
            return 0;
        }

        if(memo[cur][pre1][pre2] != null) {
            return memo[cur][pre1][pre2];
        }

        int pos = s.charAt(cur) - 'A';

        int f1 = dfs(s, cur + 1, pos, pre2, memo) + (pre1 == 26 ? 0 : getDist(pos, pre1));
        int f2 = dfs(s, cur + 1, pre1, pos, memo) + (pre2 == 26 ? 0 : getDist(pos, pre2));

        return memo[cur][pre1][pre2] = Math.min(f1, f2);
    }

    private int getDist(int a, int b) {
        int x1 = a / 6;
        int x2 = b / 6;
        int y1 = a % 6;
        int y2 = b % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
