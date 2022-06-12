package solution.biweekly80;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Match Substring After Replacement",
        difficulty = QDifficulty.HARD,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/biweekly-contest-80/problems/match-substring-after-replacement/"
)
public class Q2301 {
    /**
     * Time:  O(N * M)
     * Space: O(1)
     * */
    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        int n = s.length();
        int m = sub.length();
        boolean[][] map = new boolean[128][128];

        for(char[] mapping : mappings) {
            map[mapping[0]][mapping[1]] = true;
        }

        for(int i = 0; i <= n - m; i++) {
            boolean isOk = true;
            for(int j = i, k = 0; k < m; k++, j++) {
                if(s.charAt(j) != sub.charAt(k) && !map[sub.charAt(k)][s.charAt(j)]) {
                    isOk = false;
                    break;
                }
            }
            if(isOk) {
                return true;
            }
        }

        return false;
    }
}
