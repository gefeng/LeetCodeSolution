package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Stepping Numbers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/stepping-numbers/"
)
public class Q1215 {
    /**
     * Time:  O(2 ^ N * log(2 ^ N))
     * Space: O(2 ^ N)
     * */
    public List<Integer> countSteppingNumbers(int low, int high) {
        List<Integer> ans = new ArrayList<>();

        for(int i = 1; i < 11; i++) {
            dfs(i, -1, 0, 0, low, high, ans);
        }

        Collections.sort(ans);

        return ans;
    }

    private void dfs(int n, int preD, int curLen, int cand, int low, int high, List<Integer> ans) {
        if(curLen == n) {
            if(cand >= low) {
                ans.add(cand);
            }
            return;
        }

        if(preD == -1) {
            for(int i = n == 1 ? 0 : 1; i <= 9; i++) {
                dfs(n, i, curLen + 1, i, low, high, ans);
            }
        } else {
            int dec = preD - 1;
            int inc = preD + 1;

            if(dec >= 0) {
                long next = (long)cand * 10 + dec;
                if(next <= high) {
                    dfs(n, dec, curLen + 1, (int)next, low, high, ans);
                }

            }
            if(inc < 10) {
                long next = (long)cand * 10 + inc;
                if(next <= high) {
                    dfs(n, inc, curLen + 1, (int)next, low, high, ans);
                }
            }
        }
    }
}
