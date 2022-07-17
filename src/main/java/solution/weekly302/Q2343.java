package solution.weekly302;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Query Kth Smallest Trimmed Number",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/contest/weekly-contest-302/problems/query-kth-smallest-trimmed-number/"
)
public class Q2343 {
    /**
     * Time:  O(M * N ^ 2)
     * Space: O(M + N)
     * */
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        int[] ans = new int[m];

        for(int i = 0; i < m; i++) {
            int[] q = queries[i];
            Pair[] t = new Pair[n];

            for(int j = 0; j < n; j++) {
                t[j] = new Pair(nums[j].substring(nums[j].length() - q[1], nums[j].length()), j);
            }

            Arrays.sort(t, (a, b) -> {
                int res = a.s.compareTo(b.s);
                if(res == 0) {
                    return Integer.compare(a.i, b.i);
                }
                return res;
            });
            ans[i] = t[q[0] - 1].i;
        }

        return ans;
    }
    private class Pair {
        String s;
        int i;
        Pair(String s, int i) {
            this.s = s;
            this.i = i;
        }
    }
}
