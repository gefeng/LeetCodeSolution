package solution.weekly294;

import java.util.*;
import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum of Total Strength of Wizards",
        difficulty = QDifficulty.HARD,
        tag = QTag.PREFIX_SUM,
        url = "https://leetcode.com/contest/weekly-contest-294/problems/sum-of-total-strength-of-wizards/"
)
public class Q2281 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private long mod = (long)1e9 + 7;
    public int totalStrength(int[] strength) {
        int n = strength.length;
        long ans = 0;

        int[] l = new int[n];
        int[] r = new int[n];
        Arrays.fill(l, -1);
        Arrays.fill(r, n);

        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && strength[i] < strength[stack.peek()]) {
                stack.pop();
            }

            if(!stack.isEmpty()) {
                l[i] = stack.peek();
            }

            stack.push(i);
        }

        stack = new ArrayDeque<>();
        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && strength[i] <= strength[stack.peek()]) {
                stack.pop();
            }

            if(!stack.isEmpty()) {
                r[i] = stack.peek();
            }

            stack.push(i);
        }

        long[] psum1 = new long[n + 1];
        long[] psum2 = new long[n + 1];
        for(int i = 1; i <= n; i++) {
            psum1[i] = add(psum1[i - 1], strength[i - 1]);
        }

        for(int i = 1; i <= n; i++) {
            psum2[i] = add(psum2[i - 1], mul(strength[i - 1], (i - 1)));
        }

        for(int i = 0; i < n; i++) {
            int a = l[i] + 1;
            int b = i - 1;
            long sum1 = mul(sub(sub(psum2[b + 1], psum2[a]), mul(sub(psum1[b + 1], psum1[a]), (a - 1))), (r[i] - i));

            a = i + 1;
            b = r[i] - 1;
            long sum2 = mul(sub(mul(sub(psum1[b + 1], psum1[a]), (b + 1)), sub(psum2[b + 1], psum2[a])), (i - l[i]));

            long sum3 = mul(mul((i - l[i]), (r[i] - i)), strength[i]);

            ans = add(ans, mul(add(add(sum1, sum2), sum3), strength[i]));
        }

        return (int)ans;
    }

    private long sub(long a, long b) {
        return (a - b + mod) % mod;
    }

    private long add(long a, long b) {
        return (a + b) % mod;
    }

    private long mul(long a, long b) {
        return (a * b) % mod;
    }
}
