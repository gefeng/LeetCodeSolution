package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Number of Words Found in Sentences",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/maximum-number-of-words-found-in-sentences/"
)
public class Q2114 {
    /**
     * Time:  O(N * L)
     * Space: O(L)
     * */
    public int mostWordsFound(String[] sentences) {
        int ans = 0;
        for(String s : sentences) {
            String[] arr = s.split(" ");
            ans = Math.max(ans, arr.length);
        }

        return ans;
    }
}
