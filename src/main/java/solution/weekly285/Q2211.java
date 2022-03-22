package solution.weekly285;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Count Collisions on a Road",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/contest/weekly-contest-285/problems/count-collisions-on-a-road/"
)
public class Q2211 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int countCollisions(String directions) {
        int ans = 0;
        int n = directions.length();
        Deque<Character> stack = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            char c = directions.charAt(i);

            while(!stack.isEmpty() && ((stack.peek() == 'S' && c == 'L') ||
                    (stack.peek() == 'R' && c == 'S') ||
                    (stack.peek() == 'R' && c == 'L'))) {
                char pre = stack.pop();
                ans += pre == 'S' || c == 'S' ? 1 : 2;
                c = 'S';
            }

            stack.push(c);
        }

        return ans;
    }
}
