package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Fruits Harvested After at Most K Steps",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/maximum-fruits-harvested-after-at-most-k-steps/"
)
public class Q2106 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int mid = n;

        int[] preSum = new int[n + 1];
        for(int i = 0; i < n; i++) {
            if(fruits[i][0] >= startPos) {
                mid = i;
                break;
            }
        }

        for(int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + fruits[i - 1][1];
        }

        if(mid == 0) {
            int tot = 0;
            int idx = bsR(fruits, 0, n - 1, k, startPos);
            if(idx != -1) {
                tot = preSum[idx + 1] - preSum[0];
            }
            return tot;
        }
        if(mid == n) {
            int tot = 0;
            int idx = bsL(fruits, 0, n - 1, k, startPos);
            if(idx != -1) {
                tot = preSum[n] - preSum[idx];
            }
            return tot;
        }

        int best = 0;
        int l = mid - 1;
        int r = mid;
        for(int i = l; i >= 0; i--) {
            int tot = 0;
            int steps = 0;
            int d = startPos - fruits[i][0];
            if(d <= k) {
                tot += preSum[l + 1] - preSum[i];
            }

            if(2 * d <= k) {
                int idx = bsR(fruits, r, n - 1, k - 2 * d, startPos);
                if(idx != -1) {
                    tot += preSum[idx + 1] - preSum[r];
                }
            }

            best = Math.max(best, tot);
        }

        for(int i = r; i < n; i++) {
            int tot = 0;
            int d = fruits[i][0] - startPos;
            if(d <= k) {
                tot += preSum[i + 1] - preSum[r];
            }

            if(2 * d <= k) {
                int idx = bsL(fruits, 0, l, k - 2 * d, startPos);
                if(idx != -1) {
                    tot += preSum[l + 1] - preSum[idx];
                }
            }

            best = Math.max(best, tot);
        }

        return best;
    }

    private int bsL(int[][] f, int lo, int hi, int d, int st) {
        int idx = -1;
        while(lo <= hi) {
            int mid = lo + hi >> 1;

            if(st - f[mid][0] <= d) {
                idx = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return idx;
    }

    private int bsR(int[][] f, int lo, int hi, int d, int st) {
        int idx = -1;
        while(lo <= hi) {
            int mid = lo + hi >> 1;

            if(f[mid][0] - st <= d) {
                idx = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return idx;
    }
}
