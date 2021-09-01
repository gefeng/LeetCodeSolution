package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Shortest Word Distance",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/shortest-word-distance/"
)
public class Q243 {
    /**
     * Time:  O(N * L)
     * Space: O(1)
     * */
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int n = wordsDict.length;
        int idx1 = -1;
        int idx2 = -1;
        int res = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++) {
            String s = wordsDict[i];

            if(s.equals(word1)) {
                idx1 = i;
                if(idx2 != -1) {
                    res = Math.min(res, idx1 - idx2);
                }
            } else if(s.equals(word2)) {
                idx2 = i;
                if(idx1 != -1) {
                    res = Math.min(res, idx2 - idx1);
                }
            }
        }

        return res;
    }
}
