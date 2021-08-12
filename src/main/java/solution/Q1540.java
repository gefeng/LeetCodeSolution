package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Can Covert String in K Moves",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/can-convert-string-in-k-moves/"
)
public class Q1540 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean canConvertString(String s, String t, int k) {
        int m = s.length();
        int n = t.length();
        int[] cntDist = new int[26];

        if(m != n) {
            return false;
        }

        for(int i = 0; i < n; i++) {
            int dist = (t.charAt(i) - s.charAt(i) + 26) % 26;
            cntDist[dist]++;
        }

        for(int i = 1; i < 26; i++) {
            if(i + (cntDist[i] - 1) * 26 > k) {
                return false;
            }
        }

        return true;
    }
}
