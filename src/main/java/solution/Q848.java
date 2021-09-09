package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Shifting Letters",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/shifting-letters/"
)
public class Q848 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public String shiftingLetters(String s, int[] shifts) {
        int n = s.length();

        for(int i = n - 2; i >= 0; i--) {
            shifts[i] = (shifts[i] + shifts[i + 1]) % 26;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            int t = shifts[i] % 26;
            char c = (char)((s.charAt(i) - 'a' + t) % 26 + 'a');
            sb.append(c);
        }

        return sb.toString();
    }
}
