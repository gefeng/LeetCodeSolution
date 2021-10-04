package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Stone Game IX",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/stone-game-ix/"
)
public class Q2029 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];
        for(int s : stones) {
            cnt[s % 3]++;
        }

        for(int i = 1; i < 3; i++) {
            if(cnt[i] == 0) {
                continue;
            }

            if(cnt[0] % 2 == 0) {
                if(cnt[i] <= cnt[3 - i]) {
                    return true;
                }
            } else {
                if(cnt[i] > cnt[3 - i] + 2) {
                    return true;
                }
            }
        }

        return false;
    }
}
