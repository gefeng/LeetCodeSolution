package solution.weekly288;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Largest Number After Digit Swaps by Parity",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/contest/weekly-contest-288/problems/largest-number-after-digit-swaps-by-parity/"
)
public class Q2231 {
    /**
     * Time:  O(logN * logN)
     * Space: O(logN)
     * */
    public int largestInteger(int num) {
        char[] c = Integer.toString(num).toCharArray();
        int n = c.length;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if((c[i] - '0') % 2 == (c[j] - '0') % 2 && c[i] < c[j]) {
                    char t = c[i];
                    c[i] = c[j];
                    c[j] = t;
                }
            }
        }

        return Integer.parseInt(new String(c));
    }
}
