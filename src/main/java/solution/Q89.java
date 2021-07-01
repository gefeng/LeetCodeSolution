package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Gray Code",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/gray-code/"
)
public class Q89 {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        boolean[] seen = new boolean[1 << n];

        ans.add(0);
        seen[0] = true;
        backTrack(n, 0, ans, seen);

        return ans;
    }

    private boolean backTrack(int n, int prev, List<Integer> ans, boolean[] seen) {
        if(ans.size() == seen.length) {
            return true;
        }

        for(int i = 0; i < n; i++) {
            int curr = prev ^ (1 << i);
            if(seen[curr]) {
                continue;
            }

            seen[curr] = true;
            ans.add(curr);
            if(backTrack(n, curr, ans, seen)) {
                return true;
            }
            seen[curr] = false;
            ans.remove(curr);
        }

        return false;
    }
}
