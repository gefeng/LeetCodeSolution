package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Custom Sort String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/custom-sort-string/"
)
public class Q791 {
    /*
    * Counting sort
    * */
    public String customSortString(String S, String T) {
        int[] count = new int[26];
        for(int i = 0; i < T.length(); i++) {
            count[T.charAt(i) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            while(count[c - 'a'] != 0) {
                sb.append(c);
                count[c - 'a']--;
            }
        }

        for(char c = 'a'; c <= 'z'; c++) {
            while(count[c - 'a'] != 0) {
                sb.append(c);
                count[c - 'a']--;
            }
        }

        return sb.toString();
    }
}
