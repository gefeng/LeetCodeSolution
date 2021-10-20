package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find N Unique Integers Sum up to Zero",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/"
)
public class Q1304 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] sumZero(int n) {
        int[] ans = new int[n];

        if(n % 2 == 0) {
            for(int i = 0; i < n; i += 2) {
                ans[i] = -i - 1;
                ans[i + 1] = i + 1;
            }
        } else {
            for(int i = 0; i < n - 1; i += 2) {
                ans[i] = -i - 1;
                ans[i + 1] = i + 1;
            }
            ans[n - 1] = 0;
        }
        return ans;
    }
}
