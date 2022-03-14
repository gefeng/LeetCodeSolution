package solution.weekly284;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Artifacts That Can Be Extracted",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/contest/weekly-contest-284/problems/count-artifacts-that-can-be-extracted/"
)
public class Q2201 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        boolean[][] g = new boolean[n][n];
        int ans = 0;

        for(int[] d : dig) {
            g[d[0]][d[1]] = true;
        }

        for(int[] art : artifacts) {
            boolean isOk = true;
            for(int r = art[0]; r <= art[2]; r++) {
                for(int c = art[1]; c <= art[3]; c++) {
                    if(!g[r][c]) {
                        isOk = false;
                        break;
                    }
                }
            }

            if(isOk) ans++;
        }

        return ans;
    }
}
