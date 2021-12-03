package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Sum of Subarray Minimums",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MONOTONIC_STACK,
        url = "https://leetcode.com/problems/sum-of-subarray-minimums/"
)
public class Q907 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private static final long MOD = (long)1e9 + 7;
    public int sumSubarrayMins(int[] arr) {
        long ans = 0;
        int n = arr.length;
        Deque<Integer> deq = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            while(!deq.isEmpty() && arr[deq.peekLast()] >= arr[i]) {
                int cur = deq.pollLast();
                int pre = deq.isEmpty() ? -1 : deq.peekLast();

                long prefix = cur - pre;
                long suffix = i - cur;
                long tot = (prefix * suffix) % MOD;

                ans = (ans + (arr[cur] * tot) % MOD) % MOD;
            }

            deq.offerLast(i);
        }

        while(!deq.isEmpty()) {
            int cur = deq.pollLast();
            int pre = deq.isEmpty() ? -1 : deq.peekLast();

            long prefix = cur - pre;
            long suffix = n - cur;
            long tot = (prefix * suffix) % MOD;

            ans = (ans + (arr[cur] * tot) % MOD) % MOD;
        }

        return (int)ans;
    }
}
