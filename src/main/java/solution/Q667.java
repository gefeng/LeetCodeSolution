package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Beautiful Arrangement II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/beautiful-arrangement-ii/"
)
public class Q667 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        int l = 1;
        int r = n;
        int p = 0;

        ans[p++] = 1;
        l++;
        while(k > 1) {
            if(p % 2 == 0) {
                ans[p++] = l++;
            } else {
                ans[p++] = r--;
            }

            k--;
        }

        if(p % 2 == 0) {
            for(int i = r; i >= l; i--) {
                ans[p++] = i;
            }
        } else {
            for(int i = l; i <= r; i++) {
                ans[p++] = i;
            }
        }

        return ans;
    }
}
