package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Largest Substring Between Two Equal Characters",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/largest-substring-between-two-equal-characters/"
)
public class Q1624 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maxLengthBetweenEqualCharacters(String s) {
        int n = s.length();
        int res = -1;
        int[] idxMap = new int[26];
        Arrays.fill(idxMap, -1);

        for(int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            if(idxMap[c] != -1) {
                res = Math.max(res, i - idxMap[c] - 1);
            } else {
                idxMap[c] = i;
            }
        }

        return res;
    }
}
