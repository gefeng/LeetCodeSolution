package solution.weekly290;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Lattice Points Inside a Circle",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GEOMETRY,
        url = "https://leetcode.com/contest/weekly-contest-290/problems/count-lattice-points-inside-a-circle/"
)
public class Q2249 {
    /**
     * Time:  O(N ^ 3)
     * Space: O(1)
     * */
    public int countLatticePoints(int[][] circles) {
        int ans = 0;

        for(int i = 0; i <= 200; i++) {
            for(int j = 0; j <= 200; j++) {
                boolean isOk = false;
                for(int[] c : circles) {
                    if((i - c[0]) * (i - c[0]) + (j - c[1]) * (j - c[1]) <= c[2] * c[2]) {
                        isOk = true;
                        break;
                    }
                }
                if(isOk) {
                    ans++;
                }
            }
        }

        return ans;
    }
}
