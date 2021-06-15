package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "The Earliest and Latest Rounds Where Players Compete",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/the-earliest-and-latest-rounds-where-players-compete/"
)
public class Q1900 {
    private int n = 0;
    private int fp = 0;
    private int sp = 0;
    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        this.n = n;
        this.fp = firstPlayer;
        this.sp = secondPlayer;

        return dfs((1 << n) - 1, 1, n, 1, (n + 1) / 2, n);
    }

    private int[] dfs(int state, int p1, int p2, int r, int nrp, int pLeft) {
        if((p1 == fp && p2 == sp) || (p1 == sp && p2 == fp)) {
            return new int[] {r, r};
        }

        //System.out.println(state + " " + state1 + " " + state2);

        int nr = (pLeft - 1) == nrp ? r + 1 : r;
        nrp = r == nr ? nrp : (nrp + 1) / 2;

        int state1 = ((1 << p2 - 1) ^ state); // p1 win
        int state2 = ((1 << p1 - 1) ^ state); // p2 win
        int np1 = nr == r ? p1 + 1 : 1;
        int np2 = nr == r ? p2 - 1 : n;
        //System.out.println(p1 + " " + p2 + " " + pLeft + " " + r);
        if(p1 == fp || p1 == sp) {
            return dfs(state1, nextPlayer(state1, np1, 1), nextPlayer(state1, np2, -1),
                    nr, nrp, pLeft - 1);
        }
        if(p2 == fp || p2 == sp) {
            return dfs(state2, nextPlayer(state2, np1, 1), nextPlayer(state2, np2, -1),
                    nr, nrp, pLeft - 1);
        }

        int[] p1win = dfs(state1, nextPlayer(state1, np1, 1), nextPlayer(state1, np2, -1),
                nr, nrp, pLeft - 1);
        int[] p2win = dfs(state2, nextPlayer(state2, np1, 1), nextPlayer(state2, np2, -1),
                nr, nrp, pLeft - 1);

        return new int[] { Math.min(p1win[0], p2win[0]), Math.max(p1win[1], p2win[1]) };
    }

    private int nextPlayer(int state, int start, int dir) {
        int np = start;
        while(((1 << (np- 1)) & state) == 0) {
            np += 1 * dir;
        }
        return np;
    }
}
