package solution.weekly280;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Removing Minimum Number of Magic Beans",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/weekly-contest-280/problems/removing-minimum-number-of-magic-beans/"
)
public class Q2171 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public long minimumRemoval(int[] beans) {
        int n = beans.length;
        long[] psum = new long[n + 1];

        Arrays.sort(beans);

        for(int i = 1; i <= n; i++) {
            psum[i] = psum[i - 1] + beans[i - 1];
        }

        long ans = psum[n];
        for(int i = 0; i < n; ) {
            int j = i;
            while(j < n && beans[j] == beans[i]) {
                j++;
            }

            long keep = psum[n] - psum[j] - ((long)beans[i] * (n - j)) + psum[i];
            ans = Math.min(ans, keep);
            i = j;
        }

        return ans;
    }
}
