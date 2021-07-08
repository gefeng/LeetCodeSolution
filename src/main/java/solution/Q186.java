package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reverse Words in a String II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/reverse-words-in-a-string-ii/"
)
public class Q186 {
    /*
    * tricky problem
    * reverse each word then reverse whole string
    * */
    public void reverseWords(char[] s) {
        int n = s.length;

        for(int l = 0, r = 0; r < n; r++) {
            if(r > 0 && s[r - 1] == ' ') {
                l = r;
            }
            if(r == n - 1 || s[r + 1] == ' ') {
                reverse(s, l, r);
            }
        }

        reverse(s, 0, n - 1);
    }

    private void reverse(char[] s, int l, int r) {
        while(l < r) {
            char c = s[l];
            s[l] = s[r];
            s[r] = c;
            l++;
            r--;
        }
    }
}
