package solution.biweekly76;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Design an ATM Machine",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/contest/biweekly-contest-76/problems/design-an-atm-machine/"
)
public class Q2241 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    long[] map;
    long[] notes;
    public Q2241() {
        notes = new long[5];
        map = new long[] {20, 50, 100, 200, 500};
    }

    public void deposit(int[] banknotesCount) {
        for(int i = 0; i < 5; i++) {
            notes[i] += banknotesCount[i];
        }
    }

    public int[] withdraw(int amount) {
        long[] ans = new long[5];

        long tot = amount;
        for(int i = 4; i >= 0; i--) {
            long need = Math.min(tot / map[i], notes[i]);
            tot -= need * map[i];
        }
        if(tot != 0) {
            return new int[] {-1};
        }

        for(int i = 4; i >= 0; i--) {
            if(notes[i] == 0) {
                continue;
            }

            long need = Math.min(amount / map[i], notes[i]);

            amount -= need * map[i];
            notes[i] -= need;
            ans[i] = need;
        }

        int[] ret = new int[5];
        for(int i = 0; i < 5; i++) {
            ret[i] = (int)ans[i];
        }
        return ret;
    }
}
