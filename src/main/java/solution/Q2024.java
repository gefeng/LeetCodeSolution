package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximize the Confusion of an Exam",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/maximize-the-confusion-of-an-exam/"
)
public class Q2024 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(solve(answerKey, k, 'T'), solve(answerKey, k, 'F'));
    }

    private int solve(String s, int k, char t) {
        int ans = 0;
        int n = s.length();

        for(int l = 0, r = 0, cnt = 0; r < n; r++) {
            char c = s.charAt(r);
            if(c == t) {
                cnt++;
            }
            if(cnt > k) {
                cnt -= s.charAt(l++) == t ? 1 : 0;
            }
            if(cnt <= k) {
                ans = Math.max(ans, r - l + 1);
            }
        }

        return ans;
    }
}
