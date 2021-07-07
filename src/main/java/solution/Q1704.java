package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Determine if String Halves Are Alike",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/determine-if-string-halves-are-alike/"
)
public class Q1704 {
    public boolean halvesAreAlike(String s) {
        int n = s.length();
        int cnt = 0;
        String vowels = "aeiouAEIOU";

        for(int i = 0, j = n / 2; i < n / 2 && j < n; i++, j++) {
            if(vowels.indexOf(s.charAt(i)) != -1) {
                cnt++;
            }
            if(vowels.indexOf(s.charAt(j)) != -1) {
                cnt--;
            }
        }

        return cnt == 0;
    }
}
