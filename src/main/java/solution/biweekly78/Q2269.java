package solution.biweekly78;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find the K-Beauty of a Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/biweekly-contest-78/problems/find-the-k-beauty-of-a-number/"
)
public class Q2269 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int divisorSubstrings(int num, int k) {
        String s = Integer.toString(num);
        int n = s.length();
        int ans = 0;

        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                if(j - i + 1 == k) {
                    int d = Integer.parseInt(s.substring(i, j + 1));
                    if(d != 0 && num % d == 0) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }
}
