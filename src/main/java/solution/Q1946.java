package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Largest Number After Mutating Substring",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/largest-number-after-mutating-substring/"
)
public class Q1946 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String maximumNumber(String num, int[] change) {
        char[] ca = num.toCharArray();
        int n = ca.length;

        int i = 0;
        while(i < n && ca[i] - '0' >= change[ca[i] - '0']) {
            i++;
        }

        while(i < n && ca[i] - '0' <= change[ca[i] - '0']) {
            ca[i] = (char)(change[ca[i] - '0'] + '0');
            i++;
        }

        return new String(ca);
    }
}
