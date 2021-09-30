package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Queries one a Permutation With Key",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/queries-on-a-permutation-with-key/"
)
public class Q1409 {
    /**
     * Time:  O(N * M)
     * Space: O(N + M)
     * */
    public int[] processQueries(int[] queries, int m) {
        int n = queries.length;
        int[] ans = new int[n];
        int[] p = new int[m];
        for(int i = 0; i < m; i++) {
            p[i] = i + 1;
        }

        for(int i = 0; i < n; i++) {
            int q = queries[i];

            int j = 0;
            while(p[j] != q) {
                j++;
            }

            for(int k = j; k > 0; k--) {
                p[k] = p[k - 1];
            }
            p[0] = q;

            ans[i] = j;
        }
        return ans;
    }
}
