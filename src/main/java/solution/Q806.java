package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Lines To Write String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/number-of-lines-to-write-string/"
)
public class Q806 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int[] numberOfLines(int[] widths, String s) {
        int cnt = 1;
        int n = s.length();

        int cur = 0;
        for(int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            int w = widths[c];

            if(cur + w > 100) {
                cnt++;
                cur = w;
            } else {
                cur += w;
            }
        }

        return new int[] { cnt, cur };
    }
}
