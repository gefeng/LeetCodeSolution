package solution.weekly283;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Replace Non-Coprime Numbers in Array",
        difficulty = QDifficulty.HARD,
        tag = QTag.STACK,
        url = "https://leetcode.com/contest/weekly-contest-283/problems/replace-non-coprime-numbers-in-array/"
)
public class Q2197 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            int cur = nums[i];
            while(!stack.isEmpty() && !isCoprime(stack.peek(), cur)) {
                cur = (int)lcm(stack.pop(), cur);
            }
            stack.push(cur);
        }

        while(!stack.isEmpty()) {
            ans.add(stack.pop());
        }

        Collections.reverse(ans);

        return ans;
    }

    private boolean isCoprime(long x, long y) {
        return gcd(x, y) == 1;
    }

    private long gcd(long a, long b) {
        if(a == 0) return b;
        return gcd(b % a, a);
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
}
