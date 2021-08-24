package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Time to Type Word Using Special Typewriter",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/minimum-time-to-type-word-using-special-typewriter/"
)
public class Q1974 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minTimeToType(String word) {
        int res = 0;
        int n = word.length();

        char pre = 'a';
        for(int i = 0; i < n; i++) {
            char c = word.charAt(i);
            int d1 = (c - pre + 26) % 26;
            int d2 = 26 - d1;

            if(d1 <= d2) {
                res += d1 + 1;
            } else {
                res += d2 + 1;
            }

            pre = c;
        }

        return res;
    }
}
