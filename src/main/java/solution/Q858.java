package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Mirror Reflection",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/mirror-reflection/"
)
public class Q858 {
    /**
     * Time:  O(p)
     * Space: O(1)
     * */
    public int mirrorReflection(int p, int q) {
        int pos = 0;
        for(int i = 1; ; i++) {
            pos += q;
            pos %= 2 * p;

            if(pos == p) {
                return i % 2 == 1 ? 1 : 2;
            } else if(pos == 0) {
                return 0;
            }
        }
    }
}
