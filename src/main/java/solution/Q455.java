package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Assign Cookies",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/assign-cookies/"
)
public class Q455 {
    /**
     * Time:  O(M * logM + N * logN)
     * Space: O(logM + logN)
     * */
    public int findContentChildren(int[] g, int[] s) {
        int m = g.length;
        int n = s.length;

        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0;
        int j = 0;
        while(i < m && j < n) {
            if(g[i] <= s[j]) {
                i++;
            }
            j++;
        }

        return i;
    }
}
