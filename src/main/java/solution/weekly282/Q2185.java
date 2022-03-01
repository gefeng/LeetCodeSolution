package solution.weekly282;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Counting Words With a Given Prefix",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/weekly-contest-282/problems/counting-words-with-a-given-prefix/"
)
public class Q2185 {
    /**
     * Time:  O(N * M)
     * Space: O(1)
     * */
    public int prefixCount(String[] words, String pref) {
        int cnt = 0;
        for(String w : words) {
            if(w.startsWith(pref)) {
                cnt++;
            }
        }

        return cnt;
    }
}
