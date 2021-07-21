package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Defuse the Bomb",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/defuse-the-bomb/"
)
public class Q1652 {
    /**
     * Time:  O(N * K)
     * Space: O(N)
     * */
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] ans = new int[n];

        if(k == 0) {
            return ans;
        } else if(k < 0) {
            k = -k;
            for(int i = 0; i < n; i++) {
                for(int j = 1; j <= k; j++) {
                    ans[i] += code[((i - j) + n) % n];
                }
            }
        } else {
            for(int i = 0; i < n; i++) {
                for(int j = 1; j <= k; j++) {
                    ans[i] += code[(i + j) % n];
                }
            }
        }

        return ans;
    }
}
