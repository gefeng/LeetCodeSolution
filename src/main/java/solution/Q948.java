package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Bag of Tokens",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/bag-of-tokens/"
)
public class Q948 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int bagOfTokensScore(int[] tokens, int power) {
        int ans = 0;
        int n = tokens.length;

        Arrays.sort(tokens);

        int cur = power;
        int score = 0;
        for(int l = 0, r = n - 1; l <= r;) {
            if(cur >= tokens[l]) {
                score++;
                cur -= tokens[l++];
            } else {
                if(score >= 1) {
                    score--;
                    cur += tokens[r--];
                } else {
                    break;
                }
            }

            ans = Math.max(ans, score);
        }

        return ans;
    }
}
