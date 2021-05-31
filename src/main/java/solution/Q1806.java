package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Number of Operations to Reinitialize a Permutation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/minimum-number-of-operations-to-reinitialize-a-permutation/"
)
public class Q1806 {
    public int reinitializePermutation(int n) {
        int[] init = new int[n];
        for(int i = 0; i < n; i++) {
            init[i] = i;
        }

        int[] curr = Arrays.copyOf(init, n);
        int cnt = 0;

        while(cnt == 0 || !isEqual(curr, init)) {
            int[] next = new int[n];
            for(int i = 0; i < n; i++) {
                next[i] = i % 2 == 0 ? curr[i / 2] : curr[n / 2 + (i - 1) / 2];
            }
            cnt++;
            curr = next;
        }

        return cnt;
    }

    private boolean isEqual(int[] x, int[] y) {
        for(int i = 0; i < x.length; i++) {
            if(x[i] != y[i]) {
                return false;
            }
        }
        return true;
    }
}
