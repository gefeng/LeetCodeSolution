package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Value at a Given Index in a Bounded Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-array/"
)
public class Q1802 {
    public int maxValue(int n, int index, int maxSum) {
        int l = index;
        int r = n - 1 - index;

        int lo = 1;
        int hi = maxSum;
        int ans = 0;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            long lSum = getSum(mid - 1, l);
            long rSum = getSum(mid - 1, r);
            if(lSum + rSum + mid <= maxSum) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private long getSum(int head, int len) {
        if(len == 0) {
            return 0;
        }

        int tail = Math.max(head - len + 1, 1);
        int n = head - tail + 1;
        int left = Math.max(len - n , 0);


        return (((long)head + tail) * n) / 2 + left;
    }
}
