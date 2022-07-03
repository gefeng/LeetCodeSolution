package solution.weekly300;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Decode the Message",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/contest/weekly-contest-300/problems/decode-the-message/"
)
public class Q2325 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public String decodeMessage(String key, String message) {
        int[] map = new int[26];
        int m = key.length();
        int n = message.length();

        Arrays.fill(map, -1);

        for(int i = 0, j = 0; i < m && j < 26; i++) {
            char c = key.charAt(i);
            if(c != ' ' && map[c - 'a'] == -1) {
                map[c - 'a'] = 'a' + j++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            char c = message.charAt(i);

            if(c == ' ') {
                sb.append(c);
            } else {
                sb.append((char)map[c - 'a']);
            }
        }

        return sb.toString();
    }
}
