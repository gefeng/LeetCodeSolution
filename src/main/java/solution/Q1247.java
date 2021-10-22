package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Swaps to Make Strings Equal",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-swaps-to-make-strings-equal/"
)
public class Q1247 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minimumSwap(String s1, String s2) {
        int n = s1.length();
        int ans = 0;

        int xy = 0;
        int yx = 0;
        for(int i = 0; i < n; i++) {
            if(s1.charAt(i) == 'x' && s2.charAt(i) == 'y') {
                xy += 1;
            } else if(s1.charAt(i) == 'y' && s2.charAt(i) == 'x') {
                yx += 1;
            }
        }

        ans += xy / 2;
        ans += yx / 2;

        xy = xy % 2;
        yx = yx % 2;


        if(xy != yx) {
            return -1;
        }

        return ans + xy + yx;
    }
}
