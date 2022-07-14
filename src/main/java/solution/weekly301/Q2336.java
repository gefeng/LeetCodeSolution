package solution.weekly301;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Smallest Number in Infinite Set",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/contest/weekly-contest-301/problems/smallest-number-in-infinite-set/"
)
public class Q2336 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    boolean[] seen;
    public Q2336() {
        seen = new boolean[2000];
        Arrays.fill(seen, true);
    }

    public int popSmallest() {
        int ans = -1;
        for(int i = 1; i < 2000; i++) {
            if(seen[i]) {
                seen[i] = false;
                ans = i;
                break;
            }
        }

        return ans;
    }

    public void addBack(int num) {
        if(!seen[num]) {
            seen[num] = true;
        }
    }
}
