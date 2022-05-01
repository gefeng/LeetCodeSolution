package solution.weekly291;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Remove Digit From Number to Maximize Result",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/weekly-contest-291/problems/remove-digit-from-number-to-maximize-result/"
)
public class Q2259 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String removeDigit(String number, char digit) {
        int n = number.length();
        String ans = "";

        for(int i = 0; i < n; i++) {
            if(number.charAt(i) == digit && (i == n - 1 || number.charAt(i + 1) > number.charAt(i))) {
                ans = number.substring(0, i) + number.substring(i + 1, n);
                break;
            }
        }

        if(!ans.isEmpty()) {
            return ans;
        }

        int last = number.lastIndexOf(digit);
        ans = number.substring(0, last) + number.substring(last + 1, n);

        return ans;
    }
}
