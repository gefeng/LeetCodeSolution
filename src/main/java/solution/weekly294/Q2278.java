package solution.weekly294;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Percentage of Letter in String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/weekly-contest-294/problems/percentage-of-letter-in-string/"
)
public class Q2278 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int percentageLetter(String s, char letter) {
        int n = s.length();
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == letter) {
                cnt++;
            }
        }

        return (int)(((double)cnt / n) * 100);
    }
}
