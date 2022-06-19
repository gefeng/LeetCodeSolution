package solution.weekly298;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Greatest English Letter in Upper and Lower Case",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/contest/weekly-contest-298/problems/greatest-english-letter-in-upper-and-lower-case/"
)
public class Q2309 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public String greatestLetter(String s) {
        int n = s.length();
        boolean[] x = new boolean[26];
        boolean[] y = new boolean[26];
        String ans = "";
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(c >= 'a' && c <= 'z') {
                x[c - 'a'] = true;
            } else {
                y[c - 'A'] = true;
            }
        }

        for(int i = 25; i >= 0; i--) {
            if(x[i] && y[i]) {
                ans = "" + (char)(i + 'A');
                break;
            }
        }

        return ans;
    }
}
