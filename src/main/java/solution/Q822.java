package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Card Flipping Game",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/card-flipping-game/"
)
public class Q822 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int flipgame(int[] fronts, int[] backs) {
        int n = fronts.length;
        int ans = 2001;
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < n; i++) {
            if(fronts[i] == backs[i]) set.add(fronts[i]);
        }

        for(int i = 0; i < n; i++) {
            if(fronts[i] == backs[i]) continue;

            if(!set.contains(backs[i])) ans = Math.min(ans, backs[i]);
            if(!set.contains(fronts[i])) ans = Math.min(ans, fronts[i]);
        }

        return ans == 2001 ? 0 : ans;
    }
}
