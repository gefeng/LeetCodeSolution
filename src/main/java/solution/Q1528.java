package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Shuffle String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/shuffle-string/"
)
public class Q1528 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String restoreString(String s, int[] indices) {
        int n = s.length();
        char[] res = new char[n];

        for(int i = 0; i < n; i++) {
            res[indices[i]] = s.charAt(i);
        }

        return new String(res);
    }
}
