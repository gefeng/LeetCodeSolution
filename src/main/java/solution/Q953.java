package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;

@Problem(
        title = "Verifying an Alien Dictionary",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/verifying-an-alien-dictionary/"
)
public class Q953 {
    /**
     * Time:  O(N * L)
     * Space: O(1)
     * */
    public boolean isAlienSorted(String[] words, String order) {
        int n = words.length;
        for(int i = 0; i < n - 1; i++) {
            if(compare(words[i], words[i + 1], order) > 0) {
                return false;
            }
        }
        return true;
    }



    private int compare(String x, String y, String order) {
        int m = x.length();
        int n = y.length();

        for(int i = 0; i < m && i < n; i++) {
            char c1 = x.charAt(i);
            char c2 = y.charAt(i);
            if(c1 == c2) {
                continue;
            }
            for(int j = 0; j < 26; j++) {
                if(c1 == order.charAt(j)) {
                    return -1;
                }
                if(c2 == order.charAt(j)) {
                    return 1;
                }
            }
        }

        return m == n ? 0 : (m > n ? 1 : -1);
    }
}
