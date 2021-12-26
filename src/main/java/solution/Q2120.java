package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Execution of All Suffix Instructions Staying in a Grid",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/execution-of-all-suffix-instructions-staying-in-a-grid/"
)
public class Q2120 {
    /**
     * Time:  O(M ^ 2)
     * Space: O(M)
     * */
    private int solve(String s, int st, int n, int sr, int sc) {
        int ans = 0;

        int r = sr;
        int c = sc;
        for(int i = st; i < s.length(); i++) {
            int[] dir = new int[] {0, 0};

            if(s.charAt(i) == 'L') dir = new int[] {0, -1};
            else if(s.charAt(i) == 'R') dir = new int[] {0, 1};
            else if(s.charAt(i) == 'U') dir = new int[] {-1, 0};
            else dir = new int[] {1, 0};

            int nr = dir[0] + r;
            int nc = dir[1] + c;
            if(nr >= 0 && nc >= 0 && nr < n && nc < n) {
                r = nr;
                c = nc;
                ans++;
            } else {
                break;
            }

        }

        return ans;
    }
}
