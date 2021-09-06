package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Orderly Queue",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/orderly-queue/"
)
public class Q899 {
    /**
     * Key observation:
     * If k > 1 any two adjacent char can be swapped which indicates the string can
     * be eventually sorted.
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public String orderlyQueue(String s, int k) {
        if(k > 1) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            return new String(arr);
        }

        int n = s.length();
        String res = s;
        String cur = s;
        for(int i = 0; i < n; i++) {
            cur = cur.substring(1, n) + cur.substring(0, 1);
            if(cur.compareTo(res) < 0) {
                res = cur;
            }
        }
        return res;
    }
}
