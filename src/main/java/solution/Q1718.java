package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Construct the Lexicographically Largest Valid Sequence",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/"
)
public class Q1718 {
    /*
    * time complexity analysis
    * it looks like this problem has a n! upper bound time complexity.
    * but due to the greedy search, the real time is less.
    * */
    public int[] constructDistancedSequence(int n) {
        int[] ans = new int[n * 2 - 1];
        boolean[] used = new boolean[n + 1];
        generate(n, 0, ans, used);
        return ans;
    }

    private boolean generate(int n, int slot, int[] ans, boolean[] used) {
        if(slot == ans.length) {
            return true;
        }

        if(ans[slot] != 0) {
            return generate(n, slot + 1, ans, used);
        }

        for(int i = n; i >= 1; i--) {
            if(used[i]) {
                continue;
            }

            if(i != 1 && (slot + i > ans.length - 1 || ans[slot + i] != 0)) {
                continue;
            }

            ans[slot] = i;
            if(i != 1) {
                ans[slot + i] = i;
            }

            used[i] = true;

            if(generate(n, slot + 1, ans, used)) {
                return true;
            }

            ans[slot] = 0;
            if(i != 1) {
                ans[slot + i] = 0;
            }
            used[i] = false;
        }

        return false;
    }
}
