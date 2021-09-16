package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Probability of a Two Boxes Having The Same Number of Distinct Balls",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/probability-of-a-two-boxes-having-the-same-number-of-distinct-balls/"
)
public class Q1467 {
    /**
     * Time:  O(M ^ K * K * N)
     * Space: O(K)
     * */
    private long all = 0;
    public double getProbability(int[] balls) {
        int n = balls.length;
        int total = 0;
        for(int i = 0; i < n; i++) {
            total += balls[i];
        }

        long[] ret = dfs(balls, total, 0, 0, 0, new int[n], new int[n]);
        return (double)ret[0] / ret[1];
    }

    private long[] dfs(int[] balls, int total, int cur, int cnt1, int cnt2, int[] c1, int[] c2) {
        int n = balls.length;

        if(cnt1 > total / 2 || cnt2 > total / 2) {
            return new long[] {0, 0};
        }

        if(cur == n) {
            long p = perm(cnt1);

            if(equals(c1, c2)) {
                return new long[] {p * p, p * p};
            }
            return new long[] {0, p * p};
        }

        long[] tot = new long[2];
        for(int i = 0; i <= balls[cur]; i++) {
            c1[cur] += i;
            c2[cur] += balls[cur] - i;
            long[] ret = dfs(balls, total, cur + 1, cnt1 + i, cnt2 + balls[cur] - i, c1, c2);
            long dup = (perm(i) * perm(balls[cur] - i));
            tot[0] += ret[0] / dup;
            tot[1] += ret[1] / dup;
            c1[cur] -= i;
            c2[cur] -= balls[cur] - i;
        }

        return tot;
    }

    private long perm(int x) {
        long ans = 1;
        while(x > 1) {
            ans *= x;
            x--;
        }
        return ans;
    }

    private boolean equals(int[] c1, int[] c2) {
        int cnt1 = 0, cnt2 = 0;
        for(int i = 0; i < c1.length; i++) {
            cnt1 = c1[i] > 0 ? cnt1 + 1 : cnt1;
            cnt2 = c2[i] > 0 ? cnt2 + 1 : cnt2;
        }
        return cnt1 == cnt2;
    }
}
