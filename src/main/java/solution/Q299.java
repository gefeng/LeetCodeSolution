package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Bulls and Cows",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/bulls-and-cows/"
)
public class Q299 {
    public String getHint(String secret, String guess) {
        int[] count = new int[10];
        int bulls = 0;
        int cows = 0;
        for(int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if(s == g)
                bulls++;
            else {
                if(count[s - '0'] < 0) {
                    cows++;
                }
                if(count[g - '0'] > 0) {
                    cows++;
                }
                count[s - '0']++;
                count[g - '0']--;
            }
        }

        return bulls + "A" + cows + "B";
    }
}