package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Keep Multiplying Found Value By Two",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/keep-multiplying-found-values-by-two/"
)
public class Q2154 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int findFinalValue(int[] nums, int original) {
        int ans = original;
        Set<Integer> set = new HashSet<>();

        for(int x : nums) {
            set.add(x);
        }

        while(set.contains(ans)) {
            ans *= 2;
        }

        return ans;
    }
}
