package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Smallest String With A Given Numeric Value",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/smallest-string-with-a-given-numeric-value/"
)
public class Q1663 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String getSmallestString(int n, int k) {
        char[] c = new char[n];
        Arrays.fill(c, 'a');

        k -= n;
        for(int i = n - 1; i >= 0; i--) {
            if(k == 0) {
                break;
            }

            int offset = Math.min(k, 25);
            c[i] = (char)(c[i] + offset);
            k -= offset;
        }

        return new String(c);
    }
}
