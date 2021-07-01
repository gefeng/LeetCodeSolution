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
        int size = getPower(n);
        List<Integer> ans = new ArrayList<>();
        boolean[] seen = new boolean[size];

        ans.add(0);
        seen[0] = true;
        backTrack(n, 0, ans, seen);

        return ans;
    }

    private boolean backTrack(int n, int prev, List<Integer> ans, boolean[] seen) {
        if(ans.size() == seen.length) {
            if(cntOneBits(prev) == 1) {
                return true;
            }
            return false;
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

    private int cntOneBits(int n) {
        int cnt = 0;
        for(int i = 0; i < 16; i++) {
            if(((1 << i) & n) != 0) {
                cnt++;
            }
        }
        return cnt;
    }

    private int getPower(int n) {
        int ans = 1;
        while(n != 0) {
            ans *= 2;
            n--;
        }
        return ans;
    }
}
