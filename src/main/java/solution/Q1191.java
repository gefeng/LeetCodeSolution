package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "K-Concatenation Maximum Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/k-concatenation-maximum-sum/"
)
public class Q1191 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int kConcatenationMaxSum(int[] arr, int k) {
        int n = arr.length;
        long ans = 0;

        long cur = 0;
        long sum = 0;
        for(int i = 0; i < n; i++) {
            sum += arr[i];
            cur = Math.max(0, cur + arr[i]);
            ans = Math.max(ans, cur);
        }

        if(k == 1) {
            return (int)(ans % MOD);
        }

        long bestH = 0;
        long bestT = 0;
        cur = 0;
        for(int i = 0; i < n; i++) {
            cur += arr[i];
            bestH = Math.max(bestH, cur);
        }
        cur = 0;
        for(int i = n - 1; i >= 0; i--) {
            cur += arr[i];
            bestT = Math.max(bestT, cur);
        }
        ans = Math.max(ans, Math.max(bestT + bestH, bestT + bestH + sum * (k - 2)));

        return (int)(ans % MOD);
    }
}
