package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Detect Squares",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/detect-squares/"
)
public class Q2013 {
    /**
     * Space:  O(max(x, y) ^ 2)
     * */
    int[][] m;
    public Q2013() {
        m = new int[1001][1001];
    }

    /**
     * Time:  O(1)
     * */
    public void add(int[] point) {
        m[point[0]][point[1]]++;
    }

    /**
     * Time:  O(max(x, y))
     * */
    public int count(int[] point) {
        int ans = 0;
        int x = point[0];
        int y = point[1];

        for(int i = 0; i < 1001; i++) {
            if(m[x][i] == 0) {
                continue;
            }

            int len = Math.abs(i - y);

            if(len == 0) {
                continue;
            }

            for(int d = -1; d < 2; d += 2) {
                int xx = x + len * d;

                if(xx >= 0 && xx < 1001) {
                    ans += m[x][i] * m[xx][y] * m[xx][i];
                }
            }
        }

        return ans;
    }
}
