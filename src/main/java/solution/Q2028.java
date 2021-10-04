package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Find Missing Observations",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/find-missing-observations/"
)
public class Q2028 {
    /**
     * Time:  O(M + N)
     * Space: O(M + N)
     * */
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int[] ans = new int[n];
        int m = rolls.length;
        int sum = mean * (m + n);

        for(int r : rolls) {
            sum -= r;
        }

        int rem = sum % n;
        int avr = sum / n;

        if(avr <= 0 || avr > 6 || (avr == 6 && rem != 0)) {
            return new int[0];
        }

        Arrays.fill(ans, avr);
        int idx = 0;
        while(rem != 0) {
            ans[idx++]++;
            rem--;
        }

        return ans;
    }
}
