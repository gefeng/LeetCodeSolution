package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Replace All ?'s to Avoid Consecutive Repeating Characters",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/"
)
public class Q1576 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String modifyString(String s) {
        int n = s.length();
        char[] ca = s.toCharArray();

        for(int i = 0; i < n; i++) {
            if(ca[i] == '?') {
                if(i - 1 < 0 && i + 1 > n - 1) {
                    ca[i] = 'a';
                } else if(i - 1 < 0) {
                    ca[i] = ca[i + 1] == '?' ? 'a' : (char)((ca[i + 1] - 'a' + 1) % 26 + 'a');
                } else if(i + 1 > n - 1) {
                    ca[i] = (char)((ca[i - 1] - 'a' + 1) % 26 + 'a');
                } else {
                    if(ca[i + 1] == '?') {
                        ca[i] = (char)((ca[i - 1] - 'a' + 1) % 26 + 'a');
                    } else {
                        ca[i] = (char)((ca[i - 1] - 'a' + 1) % 26 + 'a');
                        ca[i] = ca[i] == ca[i + 1] ? (char)((ca[i + 1] - 'a' + 1) % 26 + 'a') : ca[i];
                    }
                }
            }
        }

        return new String(ca);
    }
}
