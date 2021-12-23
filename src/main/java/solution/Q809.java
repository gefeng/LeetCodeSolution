package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Expressive Words",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/expressive-words/"
)
public class Q809 {
    /**
     * Time:  O(M * max(N, L))
     * Space: O(1)
     * */
    public int expressiveWords(String s, String[] words) {
        int n = s.length();
        int ans = 0;

        for(String w : words) {
            int m = w.length();
            boolean isOk = true;

            int i = 0;
            int j = 0;
            for(; i < n && j < m;) {
                if(s.charAt(i) != w.charAt(j)) {
                    isOk = false;
                    break;
                }

                int r1 = i;
                int r2 = j;
                while(r1 < n && s.charAt(i) == s.charAt(r1)) {
                    r1++;
                }
                while(r2 < m && w.charAt(j) == w.charAt(r2)) {
                    r2++;
                }

                if(r2 - j > r1 - i || (r1 - i != r2 - j && r1 - i < 3)) {
                    isOk = false;
                    break;
                }

                i = r1;
                j = r2;
            }

            isOk &= (i == n && j == m);
            if(isOk) ans++;
        }

        return ans;
    }
}
