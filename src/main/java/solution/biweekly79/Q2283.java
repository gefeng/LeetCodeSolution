package solution.biweekly79;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if Number Has Equal Digit Count and Digit Value",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/contest/biweekly-contest-79/problems/check-if-number-has-equal-digit-count-and-digit-value/"
)
public class Q2283 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean digitCount(String num) {
        int[] cnt = new int[10];
        int n = num.length();
        for(int i = 0; i < n; i++) {
            cnt[num.charAt(i) - '0']++;
        }

        for(int i = 0; i < n; i++) {
            if(cnt[i] != num.charAt(i) - '0') {
                return false;
            }
        }

        return true;
    }
}
