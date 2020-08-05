package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;

@Problem(
        title = "Climbing Stairs",
        difficulty = QDifficulty.EASY,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/climbing-stairs/"
)
public class Q70 {
    HashMap<Integer, Integer> memo = new HashMap<>();
    public int climbStairs(int n) {
        if(n < 3)
            return n;
        int ans = 0;
        if(memo.containsKey(n))
            ans = memo.get(n);
        else {
            ans = climbStairs(n - 1) + climbStairs(n - 2);
            memo.put(n, ans);
        }
        return ans;
    }
}
