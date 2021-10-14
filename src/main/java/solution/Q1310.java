package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "XOR Queries of a Subarray",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/xor-queries-of-a-subarray/"
)
public class Q1310 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int m = queries.length;
        int[] ans = new int[m];
        int[] preXor = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            preXor[i] = preXor[i - 1] ^ arr[i - 1];
        }

        for(int i = 0; i < m; i++) {
            int[] q = queries[i];
            ans[i] = preXor[q[1] + 1] ^ preXor[q[0]];
        }

        return ans;
    }
}
