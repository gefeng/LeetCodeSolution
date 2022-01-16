package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Divide a String Into Groups of Size k",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/"
)
public class Q2138 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String[] divideString(String s, int k, char fill) {
        int padding = (k - s.length() % k) % k;

        for(int i = 0; i < padding; i++) {
            s += fill;
        }

        int n = s.length();
        int m = n / k;
        String[] ans = new String[m];
        for(int i = 0, j = 0; i < n; i += k, j++) {
            ans[j] = s.substring(i, i + k);
        }

        return ans;
    }
}
