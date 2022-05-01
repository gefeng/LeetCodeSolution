package solution.biweekly77;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Prefixes of a Given String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/biweekly-contest-77/problems/count-prefixes-of-a-given-string/"
)
public class Q2255 {
    /**
     * Time:  O(N * M)
     * Space: O(1)
     * */
    public int countPrefixes(String[] words, String s) {
        int ans = 0;
        for(String w : words) {
            if(s.startsWith(w)) {
                ans++;
            }
        }

        return ans;
    }
}
