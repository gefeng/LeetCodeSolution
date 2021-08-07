package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Unhappy Friends",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/count-unhappy-friends/"
)
public class Q1583 {
    /**
     * use a matrix to represent preference rank
     *
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int res = 0;
        int[][] m = new int[n][n];
        boolean[] unhappy = new boolean[n];

        for(int i = 0; i < n; i++) {
            int rank = n - 1;
            for(int j = 0; j < n - 1; j++) {
                m[i][preferences[i][j]] = rank--;
            }
        }

        for(int i = 0; i < n / 2; i++) {
            for(int j = 0; j < n / 2; j++) {
                if(i == j) {
                    continue;
                }

                int[] p1 = pairs[i];
                int[] p2 = pairs[j];
                if(isUnhappy(m, p1[0], p1[1], p2[0], p2[1]) || isUnhappy(m, p1[0], p1[1], p2[1], p2[0])) {
                    res += unhappy[p1[0]] ? 0 : 1;
                    unhappy[p1[0]] = true;
                }


                if(isUnhappy(m, p1[1], p1[0], p2[0], p2[1]) || isUnhappy(m, p1[1], p1[0], p2[1], p2[0])) {
                    res += unhappy[p1[1]] ? 0 : 1;
                    unhappy[p1[1]] = true;
                }


            }
        }

        return res;
    }

    private boolean isUnhappy(int[][] m, int x, int y, int u, int v) {
        return m[x][u] > m[x][y] && m[u][x] > m[u][v];
    }
}
