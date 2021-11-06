package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Alphabet Board Path",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/alphabet-board-path/"
)
public class Q1138 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String alphabetBoardPath(String target) {
        StringBuilder sb = new StringBuilder();
        int n = target.length();

        int r = 0, c = 0;
        for(int i = 0; i < n; i++) {
            int t = target.charAt(i) - 'a';

            int nr = t / 5;
            int nc = t % 5;

            if(t == 25) {
                getMoveZ(r, c, nr, nc, sb);
            } else {
                getMove(r, c, nr, nc, sb);
            }

            r = nr;
            c = nc;
        }

        return sb.toString();
    }

    private void getMove(int sr, int sc, int tr, int tc, StringBuilder sb) {
        int dr = tr - sr;
        int dc = tc - sc;

        char m = dr >= 0 ? 'D' : 'U';
        for(int j = 0; j < Math.abs(dr); j++) {
            sb.append(m);
        }

        m = dc >= 0 ? 'R' : 'L';
        for(int j = 0; j < Math.abs(dc); j++) {
            sb.append(m);
        }
        sb.append("!");
    }

    private void getMoveZ(int sr, int sc, int tr, int tc, StringBuilder sb) {
        int dr = tr - sr;
        int dc = sc - tc;
        for(int j = 0; j < dc; j++) {
            sb.append('L');
        }
        for(int j = 0; j < dr; j++) {
            sb.append('D');
        }
        sb.append('!');
    }
}
