package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Two Furthest Houses With Different Colors",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/two-furthest-houses-with-different-colors/"
)
public class Q2078 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int l = 0;
        int r = n - 1;

        while(colors[l] == colors[n - 1]) {
            l++;
        }
        while(colors[r] == colors[0]) {
            r--;
        }

        return Math.max(n - 1 - l, r);
    }
}
