package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Break a Palindrome",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/break-a-palindrome/"
)
public class Q1328 {
    /**
     * Brute force is Ok, but we go greedy.
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    public String breakPalindrome(String palindrome) {
        int len = palindrome.length();
        if(len == 1)
            return "";

        char[] sArr = palindrome.toCharArray();

        for(int i = 0; i < len / 2; i++) {
            if(sArr[i] != 'a') {
                sArr[i] = 'a';
                return String.valueOf(sArr);
            }
        }

        sArr[len - 1] = 'b'; // all 'a'
        return String.valueOf(sArr);
    }
}
