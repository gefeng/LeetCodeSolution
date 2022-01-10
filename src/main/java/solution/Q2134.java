package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Swaps to Group All 1's Together II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/"
)
public class Q2134 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n * 2];
        int tot = 0;
        int ans = n;
        for(int i = 0; i < n; i++) {
            arr[i] = nums[i];
            arr[i + n] = nums[i];
            tot += nums[i];
        }

        int cntOne = 0;
        for(int l = 0, r = 0; r < 2 * n; r++) {
            cntOne += arr[r];
            if(r - l + 1 > tot) {
                cntOne -= arr[l++];
            }

            if(r - l + 1 == tot) {
                ans = Math.min(ans, tot - cntOne);
            }
        }

        return ans;
    }
}
