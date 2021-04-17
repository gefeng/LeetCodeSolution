package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Asteroid Collision",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/asteroid-collision/"
)
public class Q735 {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < asteroids.length; i++) {
            int curr = asteroids[i];

            while(!stack.isEmpty() && stack.peek() > 0 && curr < 0) {
                int prev = stack.pop();
                if(prev == Math.abs(curr)) {
                    curr = 0;
                    break;
                } else {
                    curr = prev > Math.abs(curr) ? prev : curr;
                }
            }

            if(curr != 0)
                stack.push(curr);
        }

        int[] ans = new int[stack.size()];
        for(int i = ans.length - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }
}
