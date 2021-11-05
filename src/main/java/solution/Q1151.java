package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Swaps to Group All 1's Together",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/"
)
public class Q1151 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minSwaps(int[] data) {
        int n = data.length;
        int ans = n;

        int cntOne = 0;
        for(int x : data) {
            cntOne += x == 1 ? 1 : 0;
        }

        int swaps = 0;
        for(int l = 0, r = 0; r < n; r++) {
            if(data[r] == 0) {
                swaps++;
            }

            if(r - l + 1 > cntOne) {
                swaps -= data[l++] == 1 ? 0 : 1;
            }

            if(r - l + 1 == cntOne) {
                ans = Math.min(ans, swaps);
            }
        }

        return ans;
    }
}
