package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Find Longest Awesome Substring",
        difficulty = QDifficulty.HARD,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/find-longest-awesome-substring/"
)
public class Q1542 {
    /**
     * I like this problem.
     * palindrome like string can be,
     * odd:  "23344"
     * even: "3344"
     * use bitmask to represent current state, i.e. for "2334455"
     * it's corresponding bitmask is 0000000100. (Totally 10 bits since s contains only digits)
     * So iterate over the string, calculate current bitmask by flipping it's
     * corresponding bit. Cache state if seen first time. Remove each target state
     * from current state and check cache for farthest position.
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    public int longestAwesome(String s) {
        int n = s.length();
        int res = 0;
        int state = 0;
        int[] seen = new int[1 << 10];
        Arrays.fill(seen, -2);
        seen[0] = -1;

        for(int i = 0; i < n; i++) {
            int c = s.charAt(i) - '0';

            state ^= (1 << c);

            int even = state ^ 0;
            if(seen[even] != -2) {
                res = Math.max(res, i - seen[even]);
            }

            for(int j = 0; j < 10; j++) {
                int odd = state ^ (1 << j);
                if(seen[odd] != -2) {
                    res = Math.max(res, i - seen[odd]);
                }
            }

            if(seen[state] == -2) {
                seen[state] = i;
            }
        }

        return res;
    }
}
