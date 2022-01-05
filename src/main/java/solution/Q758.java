package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Bold Words in String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/bold-words-in-string/"
)
public class Q758 {
    /**
     * Time:  O(N * M * L)
     * Space: O(N)
     * */
    public String boldWords(String[] words, String s) {
        int n = s.length();

        boolean[] bold = new boolean[n];
        for(int i = 0; i < n; i++) {
            for(String w : words) {
                if(i + w.length() <= n && s.startsWith(w, i)) {
                    Arrays.fill(bold, i, i + w.length(), true);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n;) {
            if(bold[i]) {
                sb.append("<b>");
                int j = i;
                while(j < n && bold[j]) {
                    sb.append(s.charAt(j++));
                }
                sb.append("</b>");
                i = j;
            } else {
                int j = i;
                while(j < n && !bold[j]) {
                    sb.append(s.charAt(j++));
                }
                i = j;
            }
        }

        return sb.toString();
    }
}
